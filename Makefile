NEO = lib/concurrentlinkedhashmap-lru-1.3.1.jar:lib/neo4j-lucene-index-1.9.jar:lib/geronimo-jta_1.1_spec-1.1.1.jar:lib/neo4j-shell-1.9.jar:lib/lucene-core-3.6.2.jar:lib/neo4j-udc-1.9.jar:lib/neo4j-cypher-1.9.jar:lib/neo4j-graph-algo-1.9.jar:lib/org.apache.servicemix.bundles.jline-0.9.94_1.jar:lib/neo4j-graph-matching-1.9.jar:lib/scala-library-2.10.0.jar:lib/neo4j-jmx-1.9.jar:lib/server-api-1.9.jar:lib/neo4j-kernel-1.9.jar
SPARKSEE = lib/sparkseejava.jar
GRAPHIUM = lib/graphium.jar

LIBS = "./:$(NEO):$(SPARKSEE):$(GRAPHIUM)"
CLEAR = rm -f experiment/
COMPILE = javac -source 6 -nowarn -cp $(LIBS) experiment/

all:
	$(CLEAR)*.class
	$(COMPILE)*.java

Neo4jCreate:
	$(CLEAR)Neo4jCreate.class
	$(COMPILE)Neo4jCreate.java

Neo4jQuery:
	$(CLEAR)Neo4jQuery.class
	$(COMPILE)Neo4jQuery.java

SparkseeCreate:
	$(CLEAR)SparkseeCreate.class
	$(COMPILE)SparkseeCreate.java

SparkseeQuery:
	$(CLEAR)SparkseeQuery.class
	$(COMPILE)SparkseeQuery.java

A:
	$(CLEAR)A.class
	$(COMPILE)A.java

B:
	$(CLEAR)B.class
	$(COMPILE)B.java

C:
	$(CLEAR)C.class
	$(COMPILE)C.java

D:
	$(CLEAR)D.class
	$(COMPILE)D.java

E:
	$(CLEAR)E.class
	$(COMPILE)E.java

F:
	$(CLEAR)F.class
	$(COMPILE)F.java

Nodes:
	$(CLEAR)Nodes.class
	$(COMPILE)Nodes.java

Density:
	$(CLEAR)Density.class
	$(COMPILE)Density.java
