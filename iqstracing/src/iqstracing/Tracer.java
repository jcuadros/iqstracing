package iqstracing;

import javax.swing.JOptionPane;

public abstract class Tracer {
	String user;
	String session;
	String application;

	public Tracer(String application, String user, String session){
		this.application = application;
		this.user = user;
		this.session = session;
	}

	public abstract void trace(Event event, String time, double time_ms, int sequence);
	public abstract void trace(State state, String time, double time_ms, int sequence);

	public static String setApplication(){
		return "";//falta
	}

	public static String setUser(){
		return JOptionPane.showInputDialog("Introdueixi el nom d'usuari", "");
	}

	public static String setSession(){
		return JOptionPane.showInputDialog("Introdueixi sessi�", "");
	}
}