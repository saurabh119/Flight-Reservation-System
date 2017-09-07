
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saurabh Biyani
 */
public class Searchmanager {
    FRS_Manager frsManager;
    String Searchdate;      //date for which search is to be done
    public Flight result[][] = new Flight[20][2];   //matrix for storing the search results
    ArrayList<String> spicejet,silkair;     //spicejet and silkair arraylist for storing flight dates
    ArrayList<Integer> TotalTime;   //total flight time arraylist
    int seatsReq;         //number of seats required
    public int cnt=0;     //counter variable /keeps track of the number of flights
    /**
     * constructor of Searchmanager class
     * @param frsm 
     */
    public Searchmanager(FRS_Manager frsm){
        this.frsManager = frsm;
        this.silkair = new ArrayList<>();
        this.spicejet = new ArrayList<>();
        this.TotalTime = new ArrayList<>();
    }
    /**
     * method to search available flights 
     * @param source contains source city
     * @param Seats contains seats required
     * @param Date contains search date
     * @param Month contains search month
     * @param Day contains search say
     */
    public void searchFlight(String source,int Seats, int Date,int Month,int Day) throws IOException{
        this.seatsReq=Seats;
        this.Searchdate=Date+"/"+Month+"/2016";
        String Nextdate=(Date+1)+"/"+Month+"/2016";
        String monstr[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        String week[]={"DAILY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
        if(Date==31&&Month==10)     //when month changes from october to november
            Nextdate="1/11/2016";
        Flight f,f2;
        for(int i=0;i<frsManager.spicejet.size();i++){      //searching for spicejet flights start with this loop
            f=frsManager.spicejet.get(i);
            if(f.depCity.substring(0,4).equalsIgnoreCase(source.substring(0,4))){           //  checking if source is same
                int m1=0,m2=0,flag=0,year2=(Integer.parseInt(f.effTo.charAt(7)+"")*10 + Integer.parseInt(f.effTo.charAt(8)+""));
                int date1=(Integer.parseInt(f.effFrom.charAt(0)+"")*10 + Integer.parseInt(f.effFrom.charAt(1)+""));// 'effective from' Date
                int date2=(Integer.parseInt(f.effTo.charAt(0)+"")*10 + Integer.parseInt(f.effTo.charAt(1)+""));//'effectivre to' Date
                for(int p=0;p<12;p++)                                                    
                    if(f.effFrom.substring(3,6).equalsIgnoreCase(monstr[p]))  //checks for 'effective from' month  
                      m1=p+1;                                                           
                for(int p=0;p<12;p++)                                                   
                    if(f.effTo.substring(3,6).equalsIgnoreCase(monstr[p]))    //checks for 'effective to' month
                      m2=p+1;                                                           
                if(year2==17){    //block for checking if 'flight is effective' starts  
                    if(Month>m1)                                                        
                        flag=1;       //when the flight is effective flag becomes 1      
                    else if(Month==m1&&Date>=date1)                                     
                        flag=1;                                                         
                }                                                                       
                else if(Month>m1 && Month<m2)                                           
                    flag=1;
                else if(Month==m1&&m1==m2){
                    if(Date>=date1&&Date<=date2)
                        flag=1;
                }
                else if(Month==m1&&Date>=date1)
                    flag=1; 
                else if(Month==m2&&Date<=date2)
                    flag=1;
                if(flag==1){
                    flag=0;
                    for(int j=0;j<f.daysOfWeek.size();j++)                                                       
                        if(f.daysOfWeek.get(j).equals(week[Day]) || f.daysOfWeek.get(j).equals("DAILY")) //checks if flight is valid on that day
                            flag=1;
                }   // block for 'flight is effective' ends
                if(flag==1){
                    int startTime,endTime,maxTime,minTime,lag,diffspice=0,diffsilk=0;
                    int startHour=Integer.parseInt(f.depTime.charAt(0)+"")*10+Integer.parseInt(f.depTime.charAt(1)+"");// hour when spice journey starts
                    int startMin=Integer.parseInt(f.depTime.charAt(3)+"")*10+Integer.parseInt(f.depTime.charAt(4)+"");//min when spice journey starts
                    int endHour=Integer.parseInt(f.arrTime.charAt(0)+"")*10+Integer.parseInt(f.arrTime.charAt(1)+"");//hour when spice journey ends
                    int endMin=Integer.parseInt(f.arrTime.charAt(3)+"")*10+Integer.parseInt(f.arrTime.charAt(4)+"");//min when spice journey ends
                    startTime=startHour*60+startMin; //converted start time in minutes 
                    if(f.depTime.charAt(6)=='P')    //when start time is in 'PM' 
                        startTime+=720;                                                    
                    endTime=endHour*60+endMin;       //converted spice end time into minutes
                    if(f.arrTime.charAt(6)=='P')     //when end time is in 'PM'
                        endTime+=720;
                    if(endTime<startTime)           //when the flight journey ends on next day
                        endTime+=1440; 
                    int tmp1,tmp2;
                    tmp1=(startTime/60)*100;
                    tmp2=startTime%60;
                    if(tmp1<10){
                        f.depTime="0"+tmp1;
                    if(tmp2<10)
                        f.depTime+="0"+tmp2;
                    }
                    else
                        f.depTime=tmp1+tmp2+"";
                    tmp1=(endTime/60)*100;
                    tmp2=endTime%60;
                    if(tmp1<10){
                        f.arrTime="0"+tmp1;
                    if(tmp2<10)
                        f.arrTime+="0"+tmp2;
                    }
                    else
                        f.arrTime=tmp1+tmp2+"";
                    diffspice=(endTime-startTime)>0?endTime-startTime:1440+endTime-startTime;   //total spicejet flight time
                    minTime=endTime+120;                                                //mintime is 2 hrs after spicjet flight
                    maxTime=endTime+360;                                                //maxtime is 6 hrs after spicejet flight
                    for(int k=0;k<16;k++){                                      //  searching for the silkair flights start
                        f2=frsManager.silkair.get(k);
                        if(f.arrCity.substring(0,4).equalsIgnoreCase(f2.depCity.substring(0,4))){    //checking whether cities matches or not
                            
                            int silkStartTime = Integer.parseInt(f2.depTime);          //silkair journey start time
                            silkStartTime = (silkStartTime/100)*60+(silkStartTime%100);     //converted to minutes
                            int silkEndTime=(Integer.parseInt(f2.arrTime.substring(0,4)));      //silkair journey end time
                            silkEndTime=(silkEndTime/100)*60+(silkEndTime%100);         //converted to minutes
                            lag=(silkStartTime-endTime)>0?silkStartTime-endTime:1440+silkStartTime-endTime;     //transit time
                            diffsilk=(silkEndTime-silkStartTime)>0?silkEndTime-silkStartTime:1440+silkEndTime-silkStartTime; //total silkair flight time
                            
                            if(maxTime<1440 && silkStartTime>=minTime && silkStartTime<=maxTime){   //when maxtime is on same search date 
                                for(int j=0;j<f2.daysOfWeek.size()&&j<7;j++)
                                    if(f2.daysOfWeek.get(j).equalsIgnoreCase(week[Day].substring(0,3))) // checking if silkair flight is valid on that day
                                        if(checkremark(Searchdate,k)&&frsManager.d.checkseats(f.flightNum, Searchdate, f2.flightNum, Searchdate))//checking for remarks and number of seats
                                            addresult(f,Searchdate,f2,Searchdate,lag,diffspice,diffsilk);      // calling addresult method for adding result       
                            }
                            else if(minTime<1440&&maxTime>1440){        // when mintime is on same search date and maxtime on next date
                                String effday=week[(Day+1)%8==0?1:(Day+1)%8];   //effday is the day of next date
                                for(int j=0;j<f2.daysOfWeek.size()&&j<7;j++){
                                    if(silkStartTime>=minTime){     //when silkair flight is on same day
                                        if(f2.daysOfWeek.get(j).equalsIgnoreCase(week[Day].substring(0,3))) // checking if silkair flight is valid on that day
                                            if(checkremark(Searchdate,k)&&frsManager.d.checkseats( f.flightNum,Searchdate,f2.flightNum,Searchdate))//checking for remarks and number of seats
                                                addresult(f,Searchdate,f2,Searchdate,lag,diffspice,diffsilk);   //calling addresult method for adding result
                                    }
                                    else if(silkStartTime<=maxTime%1440){     //when silkair flight on next day
                                        if(f2.daysOfWeek.get(j).equalsIgnoreCase(effday.substring(0,3))&&!Nextdate.startsWith("14/11"))//checking if silkair flight is valid on that day
                                            if(checkremark(Nextdate,k)&&frsManager.d.checkseats(f.flightNum, Searchdate, f2.flightNum, Nextdate))//checking for remarks and number of seats
                                                addresult(f,Searchdate,f2,Nextdate,lag,diffspice,diffsilk);//calling addresult method for adding result
                                    }     
                                }
                            }
                            else if(minTime>1440&&silkStartTime>=minTime%1440&&silkStartTime<=maxTime%1440){    //when mintime is on the next date
                                String effday=week[(Day+1)%8==0?1:(Day+1)%8];   //effday is the day of next date
                                for(int j=0;j<f2.daysOfWeek.size()&&j<7;j++)
                                    if(f2.daysOfWeek.get(j).equalsIgnoreCase(effday.substring(0,3))&&!Nextdate.startsWith("14/11"))// checking if silkair flight is valid on that day
                                        if(checkremark(Nextdate,k)&&frsManager.d.checkseats(f.flightNum, Searchdate, f2.flightNum, Nextdate))//checking for remarks and number of seats
                                            addresult(f,Searchdate,f2,Nextdate,lag,diffspice,diffsilk);//calling addresult method for adding result
                            }
                        }
                    }
                }
            }
        }
    }//searching of flights ends
    /**
     * method to add available flights to the result file
     * @param spice contains spicejet flight
     * @param spdate contains spicejet flight date
     * @param silk contains silkair flight
     * @param sadate contains silkair flight date
     * @param lag contains transit time
     * @param sptime contains spicejet journey time
     * @param sitime contains silkair journey time
     */
    public void addresult(Flight spice,String spdate,Flight silk,String sadate,int lag,int sptime,int sitime){
        result[cnt][0]=spice;       //adding spicejet flight at colum no. 0 and row number cnt
        result[cnt][1]=silk;        //adding silkair flight at colum no. 0 and row number cnt
        TotalTime.add(sptime+sitime+lag);   //adding total time
        spicejet.add(spdate);       //adding spicejet flightdate
        silkair.add(sadate);        //adding silkair flightdate
        result[cnt][0].TotalTime=sptime;    //spicejet flight time
        result[cnt][1].TotalTime=sitime;    //silkair flight time
        cnt++;      //incrementing the counter 
    }//adding results ends
    /**
     * method to check remark of flight
     * @param date contains search date
     * @param p contains index number in silkair csv file
     * @return 
     */
    public boolean checkremark(String date,int p){  
        if(p==0)                    
            return true;    //retruns true when flight is valid on that date and false whne flight is invalid
        if(p==1||p==3||p==5||p==7||p==9){
            if((p!=7&&date.startsWith("30"))||date.startsWith("31"))
                return false;
            if(date.startsWith("11/11"))
                return false;
            return !(!date.startsWith("11")&&date.contains("11"));
        }
        if(p==2||p==4||p==6||p==8||p==10||p==12||p==15){
            if(p!=8&&p!=15&&(date.startsWith("30"))||date.startsWith("31"))
                return true;
            if(date.contains("11/11"))
                return true;
            return !date.startsWith("11")&&date.contains("11");
        }
        if(p==13)
            return date.startsWith("1/10")||date.startsWith("2/10")||date.startsWith("3/10");
        if(p==14){
            if(date.startsWith("1/10")||date.startsWith("2/10")||date.startsWith("3/10")||date.startsWith("4/10"))
                return false;
            else{
                if((date.startsWith("30"))||date.startsWith("31"))
                    return false;
                if(date.startsWith("11/11"))
                    return false;
                return !(!date.startsWith("11")&&date.contains("11"));
            }
        }
        if(p==11){
            if(date.startsWith("12/10")||date.startsWith("19/10")||date.startsWith("26/10"))
                return false;
            if(date.startsWith("11/11"))
                return false;
            if(!date.startsWith("11")&&date.contains("11"))
                return false;
            return !(date.startsWith("28")||date.startsWith("29")||date.startsWith("30")||date.startsWith("31"));
        }
        return true;
    }//checking of remarks ends
    /**
     * method to sort flights according to total flight time of journey
     */
    public void sortFlight(){
        int i,j,temptime;
        String tempSilkDate,tempSpiceDate;  //temporary variables for dates
        Flight tempDomFlight,tempIntFlight; //temporary variables for flights
        for(i=0;i<cnt-1;i++){
            for(j=0;j<cnt-i-1;j++){
                if(TotalTime.get(j)>TotalTime.get(j+1)){    /*if flight time of greater value is stored in lower index and next index contains lower value
                                                                then whole flight data of jth index and (j+1)th index are swapped in this block*/
                    tempDomFlight=result[j][0];      
                    tempIntFlight=result[j][1];             
                    result[j][0]=result[j+1][0];
                    result[j][1]=result[j+1][1];
                    temptime=TotalTime.get(j);
                    TotalTime.set(j,TotalTime.get(j+1));
                    TotalTime.set(j+1,temptime);
                    result[j+1][0]=tempDomFlight;
                    result[j+1][1]=tempIntFlight;
                    tempSilkDate=silkair.get(j);
                    silkair.set(j,silkair.get(j+1));
                    silkair.set(j+1,tempSilkDate);
                    tempSpiceDate=spicejet.get(j);
                    spicejet.set(j,spicejet.get(j+1));
                    spicejet.set(j+1,tempSpiceDate);
                }    
            }
        }
    }//sorting of flights ends
}
//Search manager ends