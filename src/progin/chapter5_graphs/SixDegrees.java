package progin.chapter5_graphs;

import java.util.*;

public class SixDegrees {
	
	static class Actor {
		private String name;
		
		public Actor(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	static class Movie {
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
	
	static class ActorGraph {
		private Map<String, ActorNode> nodes = new LinkedHashMap<String, ActorNode>();
		
		public ActorGraph() {}
		
		public Collection<ActorNode> getNodes() {
			return nodes.values();
		}
		
		public void addMovie(Movie movie) {
			List<Actor> actors = movie.getActors();
			// generate all pairs of actors
			for (int i = 0; i < actors.size(); i++) {
				Actor actor_i = actors.get(i);
				ActorNode actorNode_i;
				if (!nodes.containsKey(actor_i.getName())) {
					actorNode_i = new ActorNode(actor_i);
					nodes.put(actor_i.getName(), actorNode_i);
				} else {
					actorNode_i = nodes.get(actor_i.getName());
				}
				for (int j = i+1; j < actors.size(); j++) {
					Actor actor_j = actors.get(j);
					ActorNode actorNode_j;
					if (!nodes.containsKey(actor_j.getName())) {
						actorNode_j = new ActorNode(actor_j);
						nodes.put(actor_j.getName(), actorNode_j);
					} else {
						actorNode_j = nodes.get(actor_j.getName());
					}
					actorNode_i.linkWithActor(actorNode_j);
				}
			}
		}
		
		public int minimumDistance(Actor from, Actor to) {
			Queue<ActorNode> queue = new LinkedList<ActorNode>();
			Map<ActorNode, Integer> distance = new HashMap<ActorNode, Integer>();
			
			ActorNode fromNode = nodes.get(from.name);
			ActorNode toNode = nodes.get(to.name);
			
			queue.add(fromNode);
			distance.put(fromNode, 0);
			while (!queue.isEmpty()) {
				ActorNode current = queue.poll();
				for (ActorNode linkedActor : current.getLinkedActors()) {
					if (!distance.containsKey(linkedActor)) {
						distance.put(linkedActor, distance.get(current)+1);
						queue.add(linkedActor);
					}
				}
			}
			
			return (distance.containsKey(toNode) ? distance.get(toNode) : -1);
		}
	}
	
	static class ActorNode {
		private Actor actor;
		private Set<ActorNode> linkedActors = new LinkedHashSet<ActorNode>();
		
		public ActorNode(Actor actor) {
			this.actor = actor;
		}
		
		public Actor getActor() {
			return this.actor;
		}
		
		public void linkWithActor(ActorNode actor) {
			this.linkedActors.add(actor);
			actor.linkedActors.add(this);
		}
		
		public Set<ActorNode> getLinkedActors() {
			return linkedActors;
		}
	}
	
	public static void main(String[] args) {
		
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
		
		
		
		ActorGraph graph = new ActorGraph();
		graph.addMovie(mysticRiver);
		graph.addMovie(milk);
		
		System.out.println(graph.minimumDistance(kevinBacon, joshBrolin));
		System.out.println(graph.minimumDistance(kevinBacon, kevinBacon));
		System.out.println(graph.minimumDistance(kevinBacon, seanPenn));
		
	}
	
	

}
