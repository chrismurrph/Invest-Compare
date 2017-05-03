(ns scratch)

;;
;; :investor-in => investor puts money in
;; :investor-out => investor takes money out in order to spend it
;; :advisor-earnt => advisor makes money for client, dividends, cap gains from sales etc
;; :advisor-charged => all charges go in here, that have not been netted out already in :advisor-earnt
;;
(def summary
  [[:investor-in/opening-balance 688510.32]
   [:investor-in/closing-balance 668781.63]
   [:investor-in/tax-credit 1726]
   [:investor-out/pension-payment -42000]
   [:advisor-charged/plan-charges -9053.72]
   [:advisor-charged/fee-rebate 30.67]
   [:advisor-earnt/net-earnings 29568.36]
   ])

(defn acct-amount [in]
  (fn [acct]
    (->> (filter #(= (name acct) (-> % first name)) in)
         first
         second)))

(def deduction-account-namespaces #{:investor-out
                                    :advisor-charged})

(defn deductions [in]
  (reduce (fn [acc [acct amount]]
            (if (deduction-account-namespaces (-> acct namespace keyword))
              (+ acc amount)
              acc))
          0 in))

(defn x-1 []
  (deductions summary))

(defn x-2 []
  (let [get (acct-amount summary)
        earnings (+ (get :net-earnings) #_(get :tax-credit))
        minus-charges (get :plan-charges)
        investor-benefit (+ earnings #_minus-charges)
        opening (get :opening-balance)
        opening-less-deductions (+ opening minus-charges (deductions summary))
        rate (/ investor-benefit opening-less-deductions)]
    (println investor-benefit minus-charges opening-less-deductions)
    (* 100 rate)))

(defn x-3 []
  (let [get (acct-amount summary)
        minus-pension-payment (get :pension-payment)
        earnings (get :net-earnings)
        minus-charges (get :plan-charges)
        investor-benefit (+ earnings minus-charges)
        opening (get :opening-balance)]
    (/ earnings (+ opening minus-pension-payment))))

(defn rach-reply-close-his []
  (let [get (acct-amount summary)
        earnings (get :net-earnings)
        opening (get :opening-balance)
        minus-pension-payment (get :pension-payment)
        low-opening-base (+ opening minus-pension-payment)
        closing (get :closing-balance)]
    (println earnings opening minus-pension-payment low-opening-base)
    (* 100 (/ earnings low-opening-base))))

(defn my-calc-1 []
  (let [get (acct-amount summary)
        minus-charges (get :plan-charges)
        real-net-earnings (+ (get :net-earnings) minus-charges)
        opening (get :opening-balance)
        minus-pension-payment (get :pension-payment)
        low-opening-base (+ opening minus-pension-payment)]
    (* 100 (/ real-net-earnings low-opening-base))))

;;
;; For another calc do what investor would have got (i.e. less fees) over closing amount
;;
(defn my-calc-2 []
  (let [get (acct-amount summary)
        closing (get :closing-balance)
        minus-charges (get :plan-charges)
        real-net-earnings (+ (get :net-earnings) minus-charges)]
    (* 100 (/ real-net-earnings closing))))
