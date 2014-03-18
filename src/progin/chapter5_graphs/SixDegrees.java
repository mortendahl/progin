package progin.chapter5_graphs;

import java.util.*;

public class SixDegrees {	
	
	public static void main(String[] args) {
		
		// set-up actors and movies 
		Actor kevinBacon = new Actor("Kevin Bacon");
		Actor seanPenn = new Actor("Sean Penn");
		Actor joshBrolin = new Actor("Josh Brolin");
		
		Movie mysticRiver = new Movie("Mystic River");
		mysticRiver.getActors().add(kevinBacon);
		mysticRiver.getActors().add(seanPenn);
		mysticRiver.getActors().add(new Actor("Tim Robbins"));
		
		Movie milk = new Movie("Milk");
		milk.getActors().add(seanPenn);
		milk.getActors().add(joshBrolin);
		
		// link up in graph
		ActorGraph graph = new ActorGraph();
		graph.addMovie(mysticRiver);
		graph.addMovie(milk);
		
		// compute various distances from Kevin Bacon
		System.out.println(graph.minimumDistance(kevinBacon, joshBrolin));  // 2
		System.out.println(graph.minimumDistance(kevinBacon, kevinBacon));  // 0
		System.out.println(graph.minimumDistance(kevinBacon, seanPenn));  // 1
	}
}


class Actor {
	
	private String name;
	
	public Actor(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}


class Movie {
	
	private String title;
	private List<Actor> actors;
	
	public Movie(String title) {
		this(title, new ArrayList<Actor>());
	}
	
	public Movie(String title, List<Actor> actors) {
		this.title = title;
		this.actors = actors;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	// would prefer not to expose list (nor be forced to use list), but we need it for efficient pair-generation
	public List<Actor> getActors() {
		return actors;
	}
}


class ActorGraph {
	
	static class ActorNode {
		
		private Actor actor;
		private Set<ActorNode> neighbours = new LinkedHashSet<ActorNode>();
		
		public ActorNode(Actor actor) {
			this.actor = actor;
		}
		
		public Actor getActor() {
			return this.actor;
		}
		
		public void addUndirectedEdge(ActorNode other) {
			this.neighbours.add(other);
			other.neighbours.add(this);
		}
		
		public Set<ActorNode> getNeighbours() {
			return neighbours;
		}
	}
	
	private Map<Actor, ActorNode> nodes = new LinkedHashMap<Actor, ActorNode>();
	
	public ActorGraph() {}
	
	public Collection<ActorNode> getNodes() {
		return nodes.values();
	}
	
	public void addMovie(Movie movie) {
		List<Actor> actors = movie.getActors();
		
		// first make sure there's a node for each actor to simplify code below (could be in-lined to save on memory swapping)
		for (int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			if (!nodes.containsKey(actor)) {
				nodes.put(actor, new ActorNode(actor));
			}
		}
		
		// generate all pairs of actors
		for (int i = 0; i < actors.size(); i++) {
			ActorNode actorNode_i = nodes.get(actors.get(i));
			for (int j = i+1; j < actors.size(); j++) {
				ActorNode actorNode_j = nodes.get(actors.get(j));
				actorNode_i.addUndirectedEdge(actorNode_j);
			}
		}
	}
	
	public int minimumDistance(Actor from, Actor to) {
		Queue<ActorNode> queue = new LinkedList<ActorNode>();
		Map<ActorNode, Integer> distance = new HashMap<ActorNode, Integer>();
		
		ActorNode fromNode = nodes.get(from);
		ActorNode toNode = nodes.get(to);
		
		// initialise queue
		queue.add(fromNode);
		distance.put(fromNode, 0);
		// BFS until no more are reachable
		while (!queue.isEmpty()) {
			ActorNode current = queue.poll();
			for (ActorNode linkedActor : current.getNeighbours()) {
				if (!distance.containsKey(linkedActor)) {
					distance.put(linkedActor, distance.get(current)+1);
					queue.add(linkedActor);
				}
			}
		}
		
		return (distance.containsKey(toNode) ? distance.get(toNode) : -1);
	}
}
