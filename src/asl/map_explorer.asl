// Agent Explorer

/* Initial beliefs and rules */

// conditions for goal selection
is_probe_goal  			:- position(MyV) & not jia.is_probed_vertex(MyV).
is_stop_explore_goal	:- jia.is_stop_explore_goal.

/* Initial goals */

/***************** Explore goal ********************/
+!explore_map_goal
	<-	.print("Starting explore map goal");
		!select_explore_map_goal.


//+!select_explore_map_goal
//	:	is_stop_explore_goal
//	<-	!init_goal(start_new_mission(mOccupyZone));
//			!occupy_zone_goal.

+!select_explore_map_goal
	:	is_call_help_goal
	<-	!init_goal(call_help);
			!!select_explore_map_goal.

+!select_explore_map_goal
	:	is_not_need_help_goal
	<-	!init_goal(not_need_help);
			!!select_explore_map_goal.

+!select_explore_map_goal
	:	is_energy_goal
	<-	!init_goal(be_at_full_charge);
			!!select_explore_map_goal.

+!select_explore_map_goal
	:	is_disabled_goal
	<-	!init_goal(go_to_repairer);
			!!select_explore_map_goal.

+!select_explore_map_goal
	: is_escape_goal
	<-	!init_goal(escape);
			!!select_explore_map_goal.

+!select_explore_map_goal
	: is_probe_goal
	<-	!init_goal(probe);
			!!select_explore_map_goal.

+!select_explore_map_goal
	: is_survey_goal
	<- 	!init_goal(survey);
			!!select_explore_map_goal.

+!select_explore_map_goal
	:	is_buy_goal
	<-	!init_goal(explorer_buy);
			!!select_explore_map_goal.

+!select_explore_map_goal
	: is_goto_fail_goal
	<-	!init_goal(goto_failled);
			!!select_explore_map_goal.

+!select_explore_map_goal
	<-	!init_goal(move_to_neighbor_not_probed);
			!!select_explore_map_goal.



/********************* Plans ***********************/

/* Probe plans */

+!probe
   <- !do_and_wait_next_step(probe).


/* Move to not probed */

+!move_to_not_probed
	<- 	jia.move_to_not_probed(Target);
			!move_to(Target).


+!move_to_neighbor_not_probed
	<-	jia.move_to_neighbor_not_probed(Target);
			!go_to(Target).


/* Buy plans */
+!explorer_buy
	: maxEnergy(E) & E >= 30 & maxHealth(X) & X >= 5
	<- +stop_buy.

+!explorer_buy
	: buy_battery & maxEnergy(E) & E < 30
	<-	!buy(battery);
			-buy_battery;
			+buy_shield.

+!explorer_buy
	: buy_battery
	<-	-buy_battery;
			+buy_shield.

+!explorer_buy
	: buy_shield & maxHealth(X) & X < 5
	<-	!buy(shield);
			-buy_shield;
			+buy_battery.

+!explorer_buy
	: buy_shield
	<-	-buy_shield;
			+buy_battery.