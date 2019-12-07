/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

/**
 *
 * @author jaakkpaa
 */
public class QueryBuilder {
	Matcher query;	

	public QueryBuilder() {
		this.query = new All(); 
	}

	public Matcher build() {
		Matcher readyQuery = query;	
		query = new All();
		return readyQuery;
	}

	public QueryBuilder playsIn(String team) {
		query = new And(query, new PlaysIn(team));
		return this;
	}

	public QueryBuilder hasAtLeast(int quantity, String category) {
		query = new And(query, new HasAtLeast(quantity, category));
		return this;
	}

	public QueryBuilder hasFewerThan(int quantity, String category) {
		query = new And(query, new HasFewerThan(quantity, category));
		return this;
	}

	public QueryBuilder oneOf(Matcher query1, Matcher query2) {
		query = new Or(query1, query2);
		return this;
	}
		
}
