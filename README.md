# Cell Phone Usage Report

#### Ambiguities & Assumptions:

* Units for data usage unspecified. I assumed GB based on the data imported.

#### Not Finished:

I implemented reading in the CSV data entirely, and most of outputting a report to
printer. The main things still needed are:

-   Calculations for how many pages, and which record to start each page with. Can't
    assume they'll be in page index order, so just keeping an index reference isn't
    robust.
-   Adding together records in the same month for the same phone. The instructions said 
    to do columns for each month, but with a variable number of months per phone
    it makes more sense to me to do 1 row per month. Ideally I'd get clarification from
    the Product Owner before proceeding.
-   Layout and formatting for the output document. Page numbers.
-   Handling for bad data and inputs; at the very least the user should get an error
    message that tells them what went wrong in human-friendly terms. Currently it's
    just stack trace vomit when things go wrong.
-   ReportPrinter.print() is getting long, I'd prefer to break it into smaller pieces.
    I'd also like to move the calculations into a different section so that
    ReportPrinter's only job is to actually lay out the document and send it to print.
-   JavaDoc entries for classes and functions I expect would likely be reused in
    other places.
    
#### Instructions

Run `Main.main(args)` from the directory containing `CellPhone.csv` and 
`CellPhoneUsageByMonth.csv`. Command-line arguments are ignored.

Programmed using IntelliJ IDEA as my IDE.