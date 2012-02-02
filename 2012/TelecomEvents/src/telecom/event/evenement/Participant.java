package telecom.event.evenement;

import java.util.ArrayList;




public class Participant {

	private ArrayList<FileEvent> lisEvent;
	private String name;
	private int ID_PARTICIPANT;
	private String mail;
	
public Participant(String nom,int ID)
	{
		this.ID_PARTICIPANT= ID;
		this.name=nom;
		this.lisEvent=new ArrayList<FileEvent>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString()
	{
	return(this.name+"je suis un debutant en android");	
	}

public void addEvent(FileEvent ev)
{
	this.lisEvent.add(ev);
	
}
public void disPlayEvent()
{
	for(int i=0; i<this.lisEvent.size(); i++);
	{
		System.out.println("Evenement i");
	}
}
public ArrayList<FileEvent> getLisEvent() {
	return lisEvent;
}
public void setLisEvent(ArrayList<FileEvent> lisEvent) {
	this.lisEvent = lisEvent;
}
public int getID_PARTICIPANT() {
	return ID_PARTICIPANT;
}
public void setID_PARTICIPANT(int iD_PARTICIPANT) {
	ID_PARTICIPANT = iD_PARTICIPANT;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}


}
