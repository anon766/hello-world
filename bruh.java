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


if(check_node_availaibility(replication_Group_Id))
{  
    message_to_nodes=last_commited_Transaction_Id +" " +client_query;
    System.out.println("\nTable "+table_name+" exists in database\nReplication Group "+replication_Group_Id+" available. Executing query");
    response=send_transaction(message_to_nodes,replication_Group_Id);
    if(!response.equals("error") && client_query.toLowerCase().contains("select")==false && check_node_availability(replication_Group_Id) != 3)
        commit_transaction_to_log(client_query);
}

class HeartbeatAgent extends Thread{
    Socket heartbeat;
    DataOutputStream out=null;
    HeartbeatAgent(Socket heartbeat) throws IOException
    {
        this.heartbeat=heartbeat;
        out=new DataOutputStream(heartbeat.getOutputStream());
    }
    @Override
    public void run()
    {
        System.out.println("Starting heartbeat thread");
        while(!Thread.interrupted()){
            try{
                out.writeUTF("Active");
                Thread.sleep(2000);
            }
            catch(IOException e)
            {
                System.out.println("Heartbeat sending failed server down");
                e.printStackTrace();
                break;
            }
            catch(InterruptedException e)
            {
                System.out.println("Heart Beat Thread Stopping\n");
                break;
            }
        }
    }
}

public class ElectionScheduler<ID extends Serializable> {
        int electionTimeoutMs=1000;
        private volatile boolean timeoutsRunning = false;
        
        private void scheduleElectionTimeout() {
            
            System.out.println("Starting election timeout for current node", node.getId());
            timeoutsRunning = true;
            long next = this.electionTimeoutMs + random.nextInt(rangeSize);
            nextElectionTimeout.set(scheduledExecutorService.schedule(
                    this::performElectionTimeout,
                    next,
                    MILLISECONDS));
        }
        
        private void performElectionTimeout() {
            System.out.println("Election timeout occurred: server {}", server.getId());
            node.startNewElection();
            timeoutsRunning = false;
        }
    }

