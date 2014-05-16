Semantic Data Management Techniques in Graph Databases
======================================================

Full-Day Tutorial
-----------------

The tutorial describes existing approaches to model graph databases, different techniques implemented in RDF and Database engines, and their main drawbacks when a large volume of interconnected data needs to be traversed. We will focus on current solutions that have been proposed in the context of both the Semantic Web and Databases to manage large graphs. The target audience includes researchers and practitioners that develop or use query engines to consume RDF graphs. The participants will learn the properties of existing RDF and graph-based engines and how current approaches need to be extended to support  efficient graph-based  operations. A hands-on session will allow attendees to evaluate  the performance and robustness of existing approaches.

Hands-On
--------

Attendees will be able to participate in a hands-on session and implement different graph-core tasks using the API’s offered by existing graph database engines. We will provide different libraries in Java to interact with the API’s of Neo4j and Sparksee. We assume that participants have installed Java 6, and a text editor in their laptops. Mac and Linux environments are also recommended.     

The hands-on session will be comprised of the following four assignments.

1. Implement a simple query using the API’s provide by Neo4j and Sparksee.
2. Implement a set of queries using the Graphium API (http://graphium.ldc.usb.ve).
3. Implement different graph invariants using the RDF-based Graphium API (http://graphium.ldc.usb.ve/chrysalis/). 
4. Compute graph invariant of different RDF graphs and upload the results in the portal Graphium Chrysalis (http://graphium.ldc.usb.ve/chrysalis/)

Assignments
-----------

1. Implement the following query: “Papers written by Peter Smith”. Use the Sparksee API and Neo4j API.
2. Implement the following queries using the Graphium API3: 
	1. "Papers written by Peter Smith"
	2. "Papers cited by a paper written by Peter Smith that have at most 20 cites"
	3. "Papers cited by a paper written by Peter Smith or cited by papers cited by a paper written by Peter Smith"
	4. "Number of papers cited by a paper written by Peter Smith or cited by papers cited by a paper written by Peter Smith"
	5. "Number of papers cited by a paper written by Peter Smith or cited by papers cited by a paper written by Peter Smith, and have been published in ESWC"
	6. "Number of papers cited by a paper written by Peter Smith or cited by papers cited by a paper written by Peter Smith, have been published in ESWC and have at most 4 co-authors"
3. Implement graph invariants using the Graphium API
	1. Number of nodes/vertices in the graph
	2. Graph Density
