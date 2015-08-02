package typeIa.space

import typeIa.space.binaryStarType.Detatched
import typeIa.space.gameUtils.{WeightedRollable, WeightedRollTable}
import typeIa.space.starType._

import scala.util.Random

/**
 * Thing to generate a star given a seed
 */
object StarGenerator {
  class LoneStarTypeRollable(val starType: StarType) extends WeightedRollable{
    /**
     * The likelihood of rolling this. You should probably constrain this between 0 and 1, although the table
     * will normalise
     */
    override def probability: Double = starType.loneStarProbability
  }
  private val loneStars: Array[LoneStarTypeRollable] = Array(
    new LoneStarTypeRollable(ATypeMainSequence),
    new LoneStarTypeRollable(BTypeMainSequence),
    new LoneStarTypeRollable(FTypeMainSequence),
    new LoneStarTypeRollable(GTypeMainSequence),
    new LoneStarTypeRollable(KTypeMainSequence),
    new LoneStarTypeRollable(MTypeBrownDwarf),
    new LoneStarTypeRollable(RedGiant),
    new LoneStarTypeRollable(MTypeMainSequence),
    new LoneStarTypeRollable(OTypeMainSequence),
    new LoneStarTypeRollable(WhiteDwarf)
  )

  private val loneStarTable: WeightedRollTable[LoneStarTypeRollable] = new WeightedRollTable(loneStars)

  def generateLoneStarType(r: Random): StarType ={
    loneStarTable.rollDeterministic(r).starType
  }

  def generateStarData(r: Random, starType: StarType): StarData = {
    val massRange = starType.massMax - starType.massMin
    val mass = r.nextDouble() * massRange + starType.massMin
    starType.generateStarData(mass, r)
  }

  class NumStarRollable(val num: Int, val probability: Double) extends WeightedRollable

  /**
   * from wiki, Binary Stars, 2/3 of systems are single
   */
  private val numStarsTable: WeightedRollTable[NumStarRollable] = new WeightedRollTable[NumStarRollable](
    Array(
      new NumStarRollable(1, 0.66),
      new NumStarRollable(2, 0.22)
      //new NumStarRollable(3, 0.08),
      //new NumStarRollable(4, 0.04),
    )
  )
  def numStarsInSystem(r: Random): Int = {
    val roll = numStarsTable.rollDeterministic(r)
    roll.num
  }

  private val consonants = Array[String]("b", "br", "c", "ch", "ck", "d", "f", "g", "gh", "h", "j", "k", "l", "m", "mm",
    "n", "ng", "nn", "p", "pt", "qu", "r", "s", "sh", "ss", "st", "t", "th", "tr", "v", "w", "x", "y", "z")
  private val vowels = Array[String]("a", "ea", "e", "ee", "i", "o", "oo", "ou", "u")
  private val minWordLength = 2
  private val maxWordLength = 8

  private def generateWordGibberish(r: Random): String = {
    val numLetters = minWordLength + r.nextInt(maxWordLength - minWordLength)
    var i = 0
    var out = ""
    val startWithCons = r.nextInt(2)
    while(i < numLetters){
      val newFrag = if(i%2 == startWithCons)
        consonants(r.nextInt(consonants.length))
      else
        vowels(r.nextInt(vowels.length))
      out += newFrag
      i += 1
    }
    out.capitalize
  }

  private def generateNameGibberish(r: Random): String = {
    val numWords = r.nextInt(2) + 1
    var out = ""
    var i = 0

    while(i < numWords) {
      if(i > 0)
        out = out + " "
      out += generateWordGibberish(r)
      i += 1
    }
    out
  }

  private def generateName(r: Random): String = {
    generateNameGibberish(r)
  }

  def generatePrimaryStar(loc: Locpc, r: Random): GalacticObject = {
    val starType = StarGenerator.generateLoneStarType(r)
    val starData = StarGenerator.generateStarData(r, starType)
    new PrimaryStar(generateName(r), loc, starData, Array[Satellite]())
  }

  def generatePDMS(loc: Locpc, r: Random): GalacticObject= {
    val starType1 = StarGenerator.generateLoneStarType(r)
    val starType2 = if(r.nextDouble() > 0.8)
      starType1
    else
      StarGenerator.generateLoneStarType(r)

    //I made this up based on
    //www.astronomycafe.net/qadir/q333.html

    //var dist = /*r.nextDouble() * */r.nextDouble() * 2500

    //proxima centauri is about this dist from the Alpha Centauri binary, which seems a good upper limit
    var dist = r.nextDouble() * 15000
    val starData1 = StarGenerator.generateStarData(r, starType1)
    val starData2 = StarGenerator.generateStarData(r, starType2)
    var distant = false
    while(distant) {
      //from the wiki page on roche lobes
      val rocheLobe1 = 0.38 + 0.2 * scala.math.log10(starData1.mass / starData2.mass) * dist
      val rocheLobe2 = 0.38 + 0.2 * scala.math.log10(starData2.mass / starData1.mass) * dist

      if(starData1.radius > rocheLobe1 || starData2.radius > rocheLobe2) {
        dist *= 1 + r.nextDouble()
      } else
        distant = true
    }
    //from voyager.egglescliffe.org.uk/physics/gravatiation/binary/binary.html
    val totalMass = starData1.mass + starData2.mass
    val orbitalDist1 = starData2.mass* dist / totalMass
    val orbitalDist2 = starData1.mass* dist / totalMass
    val orbitVector = Utils.randomUnitLocpc(r)
    val name = generateName(r)
    new PrimaryDetachedMultipleStar(name, loc, orbitVector,
      new MultipleStar(name + " A", starData1, orbitalDist1, loc, orbitVector, true),
      new MultipleStar(name + " B", starData2, orbitalDist2, loc, orbitVector, false),
      new Detatched)
  }

  def generateStarSystem(r: Random, spaceSize: Double): GalacticObject = {
    val loc = new Locpc((r.nextDouble() - 0.5) * spaceSize, (r.nextDouble() - 0.5) * spaceSize,
    (r.nextDouble() - 0.5) * spaceSize)
    val numStars = numStarsInSystem(r)
    numStars match{
      case 1 =>
        generatePrimaryStar(loc, r)
      case 2 =>
        generatePDMS(loc, r)
    }
  }

}
