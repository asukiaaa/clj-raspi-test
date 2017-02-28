(ns raspi-test.core
  (:require [gpio.core :as gpio]
            [overtone.at-at :as at-at]))

(def at-at-pool (at-at/mk-pool))

(def button-port (gpio/open-port 22))
(def led1-port (gpio/open-port 23))
(def led2-port (gpio/open-port 24))

(defn close-ports []
  (prn :close-ports)
  (gpio/close! button-port)
  (gpio/close! led1-port)
  (gpio/close! led2-port))

(defn toggle-led []
  (let [button-value (gpio/read-value button-port)]
    (prn :button-value button-value)
    (if (= :high button-value)
      (gpio/toggle! led1-port)
      (gpio/toggle! led2-port))))

(defn -main [& args]
  (.addShutdownHook (Runtime/getRuntime)
                    (Thread. close-ports))
  (prn :start)
  (gpio/set-direction! led1-port :out)
  (gpio/set-direction! led2-port :out)
  (gpio/write-value! led1-port :high)
  (gpio/write-value! led2-port :low)
  (at-at/every 1000 toggle-led at-at-pool))
