package service;

import java.util.List;

import po.Client;
import dao.ClientDao;

public class ClientService {
	private ClientDao dao=new ClientDao();
	public static int numberOfClients=15;

	//getAll
	public List<Client> getAll(){
		return dao.selectAllClients();
	}
	//remove
	public void remove(int id ){
		dao.deleteClientById(id);
	}
	//add
	public void add(Client client){
		dao.insertClient(client);
	}
	//getClientsByPage
	public List<Client> getClientsByPage(int page){
		int end=page*numberOfClients;
		int begin=end-numberOfClients+1;
		return dao.selectClientByRange(begin, end);
				
	}
	//edit
	public void edit(Client client){
		dao.update(client.getFirmName(), client.getLinkman(), client.getLinkPhone(), client.getFirmSumm(), client.getCoCount(), client.getId());
	}
}
