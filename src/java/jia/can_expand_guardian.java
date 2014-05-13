package jia;

import java.util.Set;

import model.graph.Vertex;
import env.Percept;
import arch.MartianArch;
import arch.WorldModel;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Atom;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Term;

/**
 * Returns true or false indicating if exists a coworker in the given position.
 * </p>
 * Use: jia.has_coworker_at(+Pos);</br>
 * Where: Pos is the vertex to be checked.
 *  
 * @author mafranko
 */
public class can_expand_guardian extends DefaultInternalAction {

	private static final long serialVersionUID = 2711797878816377722L;

	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] terms) throws Exception {
		WorldModel model = ((MartianArch) ts.getUserAgArch()).getModel();
		Vertex myPosition = model.getMyVertex();

		if (model.hasActiveSoldierOrMedicOnVertex(myPosition.getId())) {
			return true;
		} else if (model.hasGreaterActiveGuardianOnVertex(myPosition.getId())) {
			return true;
		}
		return false;
	}
}
