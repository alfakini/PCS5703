package jia;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Atom;
import jason.asSyntax.ListTerm;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Term;

import java.util.ArrayList;
import java.util.List;

import env.Percept;

import model.Entity;
import model.graph.Graph;
import model.graph.Vertex;
import arch.MartianArch;
import arch.WorldModel;

/**
 * Returns true or false indicating if the agents is probably part of the teams zone.
 * </p>
 * Use: jia.is_on_team_zone(Z);</br>
 * 
 * @author mafranko
 */
public class has_not_probed_vertex extends DefaultInternalAction {

	private static final long serialVersionUID = 1168992507523306792L;
	
	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] terms) throws Exception {
		WorldModel model = ((MartianArch) ts.getUserAgArch()).getModel();
		Graph graph = model.getGraph();
		
//		Term step = terms[1];
//		System.out.println("is_on_team_zone - Agent: " + ts.getUserAgArch().getAgName() + ", Step: " + step.toString());
		
		Vertex myPosition = model.getMyVertex();
		
		Vertex v = graph.hasNotProbedVertex(myPosition);
		
		if (null == v) {
//			return un.unifies(terms[0], ASSyntax.createString("none"));
			return false;
		}
		
		String vertex = Percept.VERTEX_PREFIX + v.getId();
		return un.unifies(terms[0], ASSyntax.createString(vertex));
	}
}
