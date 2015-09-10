package typeIa.gui.pages

import com.jme3.math.{Matrix4f, Vector3f}
import com.jme3.renderer.Camera
import typeIa.gui.MainScreen
import typeIa.renderer.Renderer
import typeIa.space.Loc

/**
 * Trait for holding spinny camera functionality
 */
class SpinnyCameraManager(var camLookAt: Loc, var camDistPc: Double, var camMaxDistPc: Double) {
  var camYRot: Float = 0
  var camXRot: Float = 0

  def positionCamera(cam: Camera): Unit = {
    //teeny bit naughty. We use gfx mats to do a pc rotation.
    val mat = new Matrix4f()
    mat.angleRotation(new Vector3f(camXRot, camYRot, 0))

    val relPosPc = mat.mult(new Vector3f(0, 0, camDistPc.asInstanceOf[Float]))

    //cam.setLocation(Renderer.getNearLocGfx(Loc(camLookAt.xpc + relPosPc.x,
      //camLookAt.ypc + relPosPc.y, camLookAt.zpc + relPosPc.z)))
    cam.setLocation(Renderer.getNearLocGfx(camLookAt.pluspc(relPosPc.x, relPosPc.y, relPosPc.z)))
    cam.lookAt(Renderer.getNearLocGfx(camLookAt), new Vector3f(0, 1, 0))
  }

  def update(tpf: Float, mainScreen: MainScreen): Unit = {
    val newRot: Float = 10 * tpf
    var posCamera = false
    if(mainScreen.rightHeld) {
      camYRot += newRot
      if(camYRot > 180)
        camYRot -= 360
      posCamera = true
    }

    if(mainScreen.leftHeld) {
      camYRot -= newRot
      if(camYRot < -180)
        camYRot += 360
      posCamera = true
    }

    if(mainScreen.upHeld) {
      camXRot += newRot
      if(camXRot > 88)
        camXRot = 88
      posCamera = true
    }

    if(mainScreen.downHeld) {
      camXRot -= newRot
      if(camXRot < -88)
        camXRot = -88
      posCamera = true
    }

    if(mainScreen.equalsHeld) {
      camDistPc -= 2 * tpf
      if(camDistPc < 1)
        camDistPc = 1
      posCamera = true
    }

    if(mainScreen.minusHeld) {
      camDistPc += 2 * tpf
      if(camDistPc > camMaxDistPc)
        camDistPc = camMaxDistPc
      posCamera = true
    }

    if(posCamera)
      positionCamera(mainScreen.app.getCamera)
  }
}
