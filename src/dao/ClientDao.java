package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import po.Client;

import JDBCUtils.JDBCUtils;

public class ClientDao implements JDBCUtils.RowMapper{

	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		Client client =new Client();
		client.setCoCount(rs.getInt("cocount"));
		client.setFirmName(rs.getString("firmname"));
		client.setFirmSumm(rs.getString("firmsumm"));
		client.setId(rs.getInt("id"));
		client.setLinkPhone(rs.getString("linkphone"));
		client.setLinkman(rs.getString("linkman"));
		return client;
	}
	//selectClientById
	public Object selectClientById(int id){
		String sql ="select * from ps_client where id=?";
		return JDBCUtils.queryForObject(sql, new Object[]{id}, this);
	}
	//select all
	public List<Client> selectAllClients(){
		String sql="select * from ps_client order by id";
		return JDBCUtils.queryForList(sql, this);
	}
	//delete
	public void deleteClientById(int id){
		String sql="delete from ps_client where id=?";
		JDBCUtils.updateAll(sql, new Object[]{id});
	}
	//insert
	public void insertClient(Client client){
		String sql="insert into ps_client values(?,?,?,?,?,?)";
		JDBCUtils.updateAll(sql, new Object[]{client.getId(),client.getFirmName(),client.getLinkman(),client.getLinkPhone(),client.getFirmSumm(),client.getCoCount()});
	}
	//selectClientByRange
	//�Ҿ��÷�ҳֵһ�����̣�Ӧ���ڵ�һ��jdbc�����Ӳ�����Ѹ�ȡ��ֵ��ȡ������
	//�������Ҫ��֤�û��ڲ�ѯʱ����ɾ������ݺ�Բ�ѯ�����ʵʱ���µ�������ô���Ҫ��֤ÿ�η�ҳ��ѯ��ʱ�򲻽�����ݣ���Ҫ����ҳ������ÿ�η�ҳ��ѯ��Ҫ����jdbc����
	//���ԣ�����Ϊ�б�Ҫ��һ��jdbc�����еõ����������
	//�������������ˣ����Ѿ���jdbcUtils��д���˷��ض�����ݵĲ�ѯ���������ʹ������������ز�ѯ���ݵ���ҳ��Ļ����Ͷ�����չ�������߸��ԭʼ�ķ��������˵��������ƵĿ�ܲ�����
	//���������ȿ��ԴӲ���չ�����ĽǶ�ȥѰ�ҽ�������˼·��Ȼ����ᷢ�֣����еģ�
	//���û���ѯ��ʱ����������ݿ�����ɾ��ݣ������������ĸ����ǲ�̫�ߵģ�
	//��һ�룬�����ݵ�ʱ�����������ݣ���ķ�ҳ���ڵ�һ�β�ѯ��ʱ���Ѿ���ȷ�ˣ�ֻ����ˢ�¾Ͳ���֪����ҳ�������ˣ���������鲻����̫���Ӱ��
	//�����ѯ��ʱ����ɾ����ݣ������ɾ�˺ܶ࣬Ӱ�쵽����ҳ���������鵽����ʱ�򣬸���һ����ܰ����ʾ����
	
	//��Ȼ�������ǶԲ�ѯ���Ҫ��û��ô�ϸ��ʱ�򣬵�һ�����е��ֶΣ�������Ƕ���ݵ�����Ч�������Ҫ��ܸߵĻ�����ô��Ҫ��һ��˼��������
	//Ҳ����˵�������û��ڷ�ҳ��ѯ��ʱ�����ܵõ�ʵʱ����ݣ������һ�Ҫ��һ��jdbc�����������Ҫ�Ľ����û���Ҫ����ݵ������ʵʱ��Ϣ
	//���Ƿ�����ҳ��sql�ᷢ�֣�ÿ�η�ҳsql��ѯ���Ƕ�׵����ڲ㶼��ͨ���ѯ��������ʱ���ֻ�������
	
	public List<Client> selectClientByRange(int begin,int end){
		//�෵��һ����¼������id�ֶη�����ҳ��
		String sql="select * from(select rownum num,t.* from(select * from ps_client  order by id)t )where num between ? and ? union (select count(*),count(*),'','','','',-1 from ps_client)";
		//思路：使用union关键字在每次查询时多查一条数据，借用一个数字类型的字段传递总记录数，
		//优点是在一次jdbc连接中完成了两次sql查询，不然的话为了得到分页的总数，就要在查询每一页的时候进行两次jdbc连接
		return JDBCUtils.queryForList(sql, new Object[]{begin,end}, this);
	}
	
	public void update(String firmname,String linkman,String linkphone,String firmsumm,int cocount,int id){
		String sql="update ps_client set firmname=?,linkman=?,linkphone=?,firmsumm=?,cocount=? where id=?";
		JDBCUtils.updateAll(sql, new Object[]{firmname,linkman,linkphone,firmsumm,cocount,id});
	}
	
	
	public static void main(String []args){
		ClientDao dao=new ClientDao();
		dao.update("fi", "fi", "if", "fi", 11, 13);
		//System.out.println(Math.ceil((double)18/10));
	}

}
