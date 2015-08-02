package typeIa.space.binaryStarType

/**
 * Created by tim on 07/04/15.
 */
class Detatched extends BinaryStarType{
  /**
   * This is a bit shaky; but, basically, if we assume that 90% of stars are main sequence stars; that 2/3 of all star
   * systems are composed of single stars; that all main sequence stars are either single or in distant binaries...
   * We get an 80% probablity of this being a distant binary. Yeah, I know
   */
  override def probability: Double = 0.8
}
