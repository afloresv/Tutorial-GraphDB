Semantic Data Management Techniques in Graph Databases
======================================================

Full-Day Tutorial
-----------------

The tutorial describes existing approaches to model graph databases, different techniques implemented in RDF and Database engines, and their main drawbacks when a large volume of interconnected data needs to be traversed. We will focus on current solutions that have been proposed in the context of both the Semantic Web and Databases to manage large graphs. The target audience includes researchers and practitioners that develop or use query engines to consume RDF graphs. The participants will learn the properties of existing RDF and graph-based engines and how current approaches need to be extended to support  efficient graph-based  operations. A hands-on session will allow attendees to evaluate  the performance and robustness of existing approaches.

Hands-On
--------

Attendees will be able to participate in a hands-on session and implement different graph-core tasks using the API’s offered by existing graph database engines. We will provide different libraries in Java to interact with the API’s of Neo4j and Sparksee. We assume that participants have installed Java 6, and a text editor in their laptops. Mac and Linux environments are also recommended.

Assignments
===========

The hands-on session will be comprised of four assignments.

First of all, open the terminal an go to the Hands-On folder. You've been given an almost complete code, for the previous assignments at the `experiment/` folder. You have to complete the code where is indicated with an `INSERT CODE HERE` or `INSERT String HERE` inside a comment.

1) Implement the query “_Papers written by Peter Smith_” using the [Sparksee API](http://sparsity-technologies.com/downloads/javadoc-java/index.html) and [Neo4j API](http://api.neo4j.org/2.1.0-M01/).
----

Complete the code for Neo4j, located in the files `experiment/Neo4jCreate.java` and `experiment/Neo4jQuery.java`. Then..

	> make Neo4jCreate
	> make Neo4jQuery
	> ./publications Neo4jCreate neo4j_db
	> ./publications Neo4jQuery neo4j_db

Now complete the code for Sparksee, located in the files `experiment/SparkseeCreate.java` and `experiment/SparkseeQuery.java`. Then..

	> make SparkseeCreate
	> make SparkseeQuery
	> ./publications SparkseeCreate sparksee_db
	> ./publications SparkseeQuery sparksee_db

2) Implement the following queries using the [Graphium API](http://graphium.ldc.usb.ve).
----

First, let's create the Graphium DB from the _NT_ file `publications.nt`:

	> ./create <Neo4j or Sparksee> publications.nt graphium_db

Now...

* _Papers written by Peter Smith_

Complete the code in `experiment/A.java`, and run (in the terminal)...

	> make A
	> ./publications A graphium_db

* _Papers cited by a paper written by Peter Smith that have at most 2 cites_

Complete the code in `experiment/B.java`, and run (in the terminal)...

	> make B
	> ./publications B graphium_db

* _Papers cited by a paper written by Peter Smith or cited by papers cited by a paper written by Peter Smith_

Complete the code in `experiment/C.java`, and run (in the terminal)...

	> make C
	> ./publications C graphium_db

* _Number of papers cited by a paper written by Peter Smith or cited by papers cited by a paper written by Peter Smith_

Complete the code in `experiment/D.java`, and run (in the terminal)...

	> make D
	> ./publications D graphium_db

* _Number of papers cited by a paper written by Peter Smith or cited by papers cited by a paper written by Peter Smith, and have been published in ESWC_

Complete the code in `experiment/E.java`, and run (in the terminal)...

	> make E
	> ./publications E graphium_db

* _Number of papers cited by a paper written by Peter Smith or cited by papers cited by a paper written by Peter Smith, have been published in ESWC and have at most 4 co-authors_

Complete the code in `experiment/F.java`, and run (in the terminal)...

	> make F
	> ./publications F graphium_db

3) Implement graph invariants using the Graphium API.
----

* Number of nodes/vertices in the graph.

Complete the code in `experiment/Nodes.java`, and run (in the terminal)...

	> make Nodes
	> ./publications Nodes graphium_db

* Graph Density.

Complete the code in `experiment/Density.java`, and run (in the terminal)...

	> make Density
	> ./publications Density graphium_db

4) Compute graph invariant of different RDF graphs, using the Chrysalis tool, and upload the results in the [Graphium Chrysalis website](http://graphium.ldc.usb.ve/chrysalis/).
---

A
