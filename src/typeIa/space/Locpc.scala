package typeIa.space

/**
 * Space location in parsecs. A parsec is 3.26ly, or 31 trillion km, or 2.0626*10^5 au. 206264.806 au
 * Proxima centauri is about 1.3 parsecs from the sun: the sun is about 8000 pc from the centre of the milky way;
 * which has a diamater of 34000pc
 *
 * Origin is game origin, the centre of the neighborhood
 */
case class Locpc(x: Double, y: Double, z: Double) {
  def +(r: Locpc): Locpc = new Locpc(x + r.x, y + r.y, z + r.z)
  def -(r: Locpc): Locpc = new Locpc(x - r.x, y - r.y, z - r.z)
  def *(d: Double): Locpc = new Locpc(x * d, y * d, z * d)
  def length: Double = math.sqrt(x * x + y * y + z * z)
}
