package ece641.March11th.dblayout;

import ece641.March11th.entities.Contact;
import ece641.March11th.entities.Data;
import ece641.March11th.entities.User;

public interface DatabaseUpdate {

	public void updateUser(User user);
	public void updateContact(Contact Contact);
	public void updateData(Data data);
}
