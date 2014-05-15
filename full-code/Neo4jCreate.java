/*
 *  Copyright (C) 2014, Universidad Simon Bolivar
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package experiment;

import java.util.*;
import java.lang.*;
import java.io.*;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.factory.*;
import org.neo4j.graphdb.traversal.*;
import org.neo4j.tooling.*;
import org.neo4j.kernel.*;
import org.neo4j.helpers.collection.*;

public class Neo4jCreate {

	public static GraphDatabaseService g;
	public static GlobalGraphOperations globalOP;
	public static IndexManager indexManager;
	public static Index<Node> index;
	public static Transaction tx;

	public static void main(String[] args) {

		g = new GraphDatabaseFactory().
			newEmbeddedDatabaseBuilder(args[0]).
			loadPropertiesFromFile("conf/neo4j.properties").
			newGraphDatabase();
		globalOP = GlobalGraphOperations.at(g);
		indexManager = g.index();
		index = indexManager.forNodes("id");

		tx = g.beginTx();
		try {

			Node paper1 = addNode("Paper1");
			Node paper2 = addNode("Paper2");
			Node paper3 = addNode("Paper3");
			Node paper4 = addNode("Paper4");
			Node paper5 = addNode("Paper5");

			Node eswc = addNode("ESWC");
			Node iswc = addNode("ISWC");

			Node peter_smith = addNode("Peter Smith");
			Node john_smith  = addNode("John Smith");
			Node juan_perez  = addNode("Juan Perez");

			DynamicRelationshipType conference, author, cites;

			conference = DynamicRelationshipType.withName("conference");
			addRelationship(paper1,conference,eswc);
			addRelationship(paper2,conference,iswc);
			addRelationship(paper3,conference,eswc);
			addRelationship(paper4,conference,eswc);
			addRelationship(paper5,conference,iswc);

			author = DynamicRelationshipType.withName("author");
			addRelationship(paper1,author,peter_smith);
			addRelationship(paper2,author,john_smith);
			addRelationship(paper3,author,peter_smith);
			addRelationship(paper3,author,juan_perez);
			addRelationship(paper4,author,john_smith);
			addRelationship(paper4,author,juan_perez);
			addRelationship(paper5,author,peter_smith);
			
			cites = DynamicRelationshipType.withName("cites");
			addRelationship(paper1,cites,paper2);
			addRelationship(paper3,cites,paper1);
			addRelationship(paper3,cites,paper4);
			addRelationship(paper4,cites,paper2);

		} finally {
			tx.success();
			tx.finish();
			g.shutdown();
		}
	}

	public static Node addNode(String id) {
		// CHECK THIS OUT
		// This is how you create a new node in Neo4j
		Node n = g.createNode();
		n.setProperty("id",id);
		index.add(n,"id",id);
		return n;
	}

	public static void addRelationship(Node src, DynamicRelationshipType rel, Node dst) {
		// CHECK THIS OUT
		// This is how you create a relationship in Neo4j
		src.createRelationshipTo(dst,rel);
	}
}
