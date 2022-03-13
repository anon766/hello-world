

if(check_node_availaibility(replication_Group_Id))
{  
    message_to_nodes=last_commited_Transaction_Id +" " +client_query;
    System.out.println("\nTable "+table_name+" exists in database\nReplication Group "+replication_Group_Id+" available. Executing query");
    response=send_transaction(message_to_nodes,replication_Group_Id);
    if(!response.equals("error") && client_query.toLowerCase().contains("select")==false && check_node_availability(replication_Group_Id) != 3)
        commit_transaction_to_log(client_query);
}


int check_node_availaibility(int replication_Group_Id)
{
     int c=0;
     for(int j=replication_Group_Id;j<9;j+=3)
         if(CentralServer.available[j]==true)
             c++;
     if(c<2)
         return 0;
     return c;
 }
