(ns raspi-test.core
  (:require [gpio.core :as gpio]))

(def button-pin 3)
(def led1-pin 4)
(def led2-pin 5)

(defn toggle-led []
  (let [button-value (gpio/read-value button-pin)]
    (prn :button-value button-value)
    (if (= :high button-value)
      (gpio/write-value led1-pin true)
      ;;(gpio/toggle-value led1-pin)
      (gpio/toggle-value led2-pin))
    (delay 1000)))

(defn -main [& args]
  ;(gpio/open-pin button-pin)
  ;(gpio/open-pin led1-pin)
  ;(gpio/open-pin led2-pin)
  (prn :button (gpio/read-value button-pin))
  (gpio/set-direction led1-pin :out)
  (gpio/set-direction led2-pin :out)
  (toggle-led))

;; runtime shutdown hook
;; tools.namespace
;; with-open
