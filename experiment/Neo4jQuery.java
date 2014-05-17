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

public class Neo4jQuery {

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

		Node v;
		Relationship rel;
		Iterator<Relationship> it;

		// Find the node for 'Peter Smith'
		v = index.get("id",/* INSERT String HERE */).getSingle();
		if (v == null) return;

		// Get incoming relationships with type 'author'
		DynamicRelationshipType author = DynamicRelationshipType.withName("author");
		it = v.getRelationships(author,Direction.INCOMING).iterator();
		while (it.hasNext()) {
			rel = it.next();
			System.out.println(rel./* INSERT CODE HERE */.getProperty("id"));
			// HINT: Use 'getStartNode()' or 'getEndNode()'
			// Which one you need?
		}

		g.shutdown();
	}
}
