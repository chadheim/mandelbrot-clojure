(ns mandelbrot.generator
  (:gen-class))

(import java.io.File)
(import java.awt.Color)
(import java.awt.image.BufferedImage)
(import javax.imageio.ImageIO)

(defn calc-iterations [x y max]
  (loop [p 0.0 q 0.0 i 0]
    (if (or (>= i max) (>= (+ (* p p) (* q q)) 4.0))
      i
      (recur (+ (- (* p p) (* q q)) x) (+ (* 2.0 p q) y) (inc i)))))

(defn calc-color [i max]
  (if (= i max)
    (Color. 0 0 0)
    (let [s (/ i max)
          r (Math/min 1.0 (* s 2.0))
          g (Math/min 1.0 (* s 1.0))
          b (Math/min 1.0 (* s 4.0))]
      (Color. r g b))))

(defn -main
  [& args]
  (let [img-w 1920 img-h 1080 max 100
        vp-l -2.5 vp-r 1.0 vp-t -1.0 vp-b 1.0
        bi (BufferedImage. img-w img-h BufferedImage/TYPE_INT_RGB)
        g (.createGraphics bi)]
    (doseq [px (range img-w)
            py (range img-h)]
      (let [x (+ (* (/ px img-w) (- vp-r vp-l)) vp-l)
            y (+ (* (/ py img-h) (- vp-b vp-t)) vp-t)]
        (.setColor g (calc-color (calc-iterations x y max) max))
        (.drawLine g px py px py)))
    (ImageIO/write bi "png" (File. "mandelbrot.png"))))

