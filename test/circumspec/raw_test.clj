(ns circumspec.raw-test
  (:use circumspec circumspec.raw)
  (:import java.io.File)
  )

;; TODO: better filename convention
(describe dump-file
  (it "creates a file in .circumspec/raw"
    (let [file (dump-file)
		  separator (java.io.File/separator)
		  escapedSeparator (if (= separator "\\") "\\\\" (str separator))
		  filePattern (str "[.]circumspec" escapedSeparator "raw" escapedSeparator ".*")
		  ]
      (should (instance? java.io.File file))
	  (should (not (re-find #":" (.toString file)))) 
      (should (re-find (re-pattern filePattern) (.toString file))))))

(describe dump-results
  (it "writes complete results to a file"
    (let [file (dump-results (take 2 (repeat {:sample true})))]
      (should (= "{:sample true}\n{:sample true}\n" (slurp (.toString file))))
      (should (.delete file) "deleting sample result data"))))