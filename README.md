
Invest Compare
==============

You currently have a financial planner and want to compare his/her performance against a given wholesale fund.

On the one hand we have all the transactions with your financial planner.
This is money going into and coming out of his *fund* - essentially fund distributions & capital gains from the sale of units held in particular funds, fees charged by the planner, and then capital that the investor introduces and withdraws.
On the other hand we have *what if* this money was invested with a particular wholesale fund.

The investment period will be from the day the first amount was introduced to the financial planner's *fund* up until today, or whenever financial data is collected up until.

Financial planner
-----------------

Money is either going into the *fund* or coming out of it.
There are two actors: the investor and the (financial) planner.
With two actors and two types of events then on any date zero or more of four things can take place:
1. investor puts money in
2. investor takes money out
3. planner puts money in (proceeds of his investments)
4. planner takes money out (his own charges, assming that individual fund charges have already been netted out)

Can we can get this information from existing reports already delivered to the investor/client? Looking at a paper report for one year the answer is yes - all this data is reported in the "Transaction Details and Costs Summary". One complication is that unrealised capital gains are calculated at the end of the year using the market rate of the fund. When doing the calculation over many years, as in the whole of the investment period, many of these unrealised gains will have become realised. For this reason we really do need to track the purchase and sale of units in every fund, realising capital gains/losses when they occur, and only using unrealised amounts on the end date of the investment period, which will span many years.

With a lot of transactions over many years it would be good to get hold of easily parsed electronic versions of the annual statements. Just the "Transaction Details and Costs Summary" should be enough as we will only need the "Account Valuation as at..." for the last period, which can be easily manually entered from the last paper statement.

Given that the "Transaction Details and Costs Summary" is simply a series of line items an appropriate format would be .csv files, one for each financial year. However any readable format (XML etc) will be fine.

Wholesale fund
--------------
The unit price of the wholesale fund varies every day over the investment period.
It is assumed we know this stream of unit prices on a daily basis.
Unfortunately it can't be assumed that the unit price stream abstracts over all the fees of the wholesale fund. These extra charges will need to be read up on and known about so we can impute them. There will need to be a *fees-formula* in order to do this.

Once these assumptions have been dealt with the only other input is to apply 1 and 2 from above. We need to simulate that the investor puts money in and takes it out from this wholesale fund. There is no need for IRR calculations if we record amounts of money at unit prices - think of it as bags of money at unit prices. The current valuation will depend on what the unit price is today for all these bags of money.

As for how to apply the *fees-formula* - applying it should produce money coming out on particular days, in response to the passing of a certain amount of time or some user action. Perhaps each of these *fees money out* events should be the same as the investor taking money out, but the money essentially goes into a black hole as far as calculating the current valuation is concerned.  

Result
------
How well the financial planner did compared to the wholesale fund in one amount, in terms of today's dollars.
This will be negative if the financial planner did not do as well as the wholesale fund.
