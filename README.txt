Requires Java 8+ and Maven.

Use run.sh to execute the project.

The results will be in results.txt.

---

Some minor observations about this assignment:

1. The listings.txt JSON file should ideally have the JSON objects wrapped in an array. It seems awkward to me to read
it in line by line.
2. Similarly, the output seems a bit awkward for the same reason.

---

For this particular dataset, I did not use any backing database. For a larger project I would, but this assignment
seems to be more of a one-off batch processing script than a full solution. Using a database would be useful if listings
or products were being added or removed periodically, rather than handling a bulk amount of data.

Another consideration I had, but intentionally ignored in this case was memory consumption. The overhead for this small
amount of data was negligable. For a larger set of data, I might consider batch processing a set of listings against a
subset of the products. This could be run serially or in a distributed manner. For really large jobs, I'd probably look
at a technology like Apache Spark or Hadoop to perform distributed processing.

I left many of the fields as String values, even though they aren't really Strings. I made a Gson converter for the
announced-date field in the product object. This was to demonstrate that I do value converting values to strong Java
types, when possible. I just wanted to keep to the specification provided as much as possible for the other fields.

I provided a pluggable interface for figuring out which product listings are matched with which product. I used a
somewhat niave approach that iterates through all the objects in each list for clarity. If I needed to make a more
efficient algorithm, the interface would easily allow me to make one that runs faster.

I also provided a pluggable interface for determining which product listing matches which product. I played around with
a few simple ideas before settling on a solution that would yeild few false-positives. It could likely be improved using
a combination of a keyword dictionary and something similar to the Google page-rank algorithm to give a better estimate
of the relevance of the tokens to a particular product.

Usually, I'd be adding a suite of tests to a project, but scripty work like this really doesn't warrant extensive
testing, in my opinion. From the type of assignment, it seems like this would be a one-off sort of project. If that's
the case, then, generally, tweaking the script parameters and manually validating the output is usually more effective
than building automated testing. I might have made some sample data to test against the script if I was worried about
the validity of the results.

I like to be pragmatic, so I tried to demonstrate some good engineering principles with this assignment without going
overboard and shooting way outside of its scope.
