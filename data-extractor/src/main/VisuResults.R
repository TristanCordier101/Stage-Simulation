library(RPostgreSQL)
library(postGIStools)
library(ggplot2)
library(tidyverse)
library(dplyr)
library(reshape2)

con <- dbConnect(PostgreSQL(), dbname = "tomsa", user = "tomsa",
                 host = "localhost",
                 password = "tomsa",
                 port = "5432")

config <- dbGetQuery(con, "SELECT * from configuration_state")


plotSimuNoEquipTrans(13)
plotSimuNoEquip(23)
plotSimu(24)
plotSimu(1356)



landState <- dbGetQuery(con, "SELECT * FROM land_state")
propertyState<- dbGetQuery(con, "SELECT * FROM property_state")
promoterState<- dbGetQuery(con, "SELECT * FROM promoter_state")
investorState<- dbGetQuery(con, "SELECT * FROM investor_state")
householdState<- dbGetQuery(con, "SELECT * FROM household_state")

OnelandState <- dbGetQuery(con, "SELECT * FROM land_state where idsimulation = 1429")
OnepropertyState<- dbGetQuery(con, "SELECT * FROM property_state where idsimulation = 1429")
OnepromoterState<- dbGetQuery(con, "SELECT * FROM promoter_state where idsimulation = 1429")
OneinvestorState<- dbGetQuery(con, "SELECT * FROM investor_state where idsimulation = 1429")
OnehouseholdState<- dbGetQuery(con, "SELECT * FROM household_state where idsimulation = 1429")


boxplot(OnehouseholdState$purchasingpower~OnehouseholdState$step,main="Household Purchasing Power vs Step")
boxplot(OnehouseholdState$netmonthlyincome~OnehouseholdState$step, main="Household Mounthly income vs Step")
boxplot(OnepromoterState$purchasingpower~OnepromoterState$step,main="Promoter Purchasing Power vs Step")
boxplot(OneinvestorState$purchasingpower~OneinvestorState$step,main="Investor Purchasing Power vs Step")
boxplot(OneinvestorState$monthlyincome~ OneinvestorState$step,main="Investor Mounthly income vs Step")
boxplot(OnelandState$price~OnelandState$step,main="Land price")


hist(OnepromoterState$step)

#plot propertyState
ggplot(OnepropertyState, aes(factor(OnepropertyState$state))) + geom_bar(stat="count")

##Plot mean One Simu

MeanHouseholdPower = tapply(OnehouseholdState$purchasingpower,INDEX = OnehouseholdState$step,FUN   = mean)
MeanInvestor = tapply(OneinvestorState$purchasingpower,INDEX = OneinvestorState$step,FUN   = mean)
MeanPromo = tapply(OnepromoterState$purchasingpower,INDEX = OnepromoterState$step,FUN   = mean)

plot(MeanHouseholdPower)
lines(MeanHouseholdPower)
lines(MeanInvestor, col="darkblue")
lines(MeanPromo)


## Plot mean all simu 
plotMeanPurchasing(26,146,1)
plotMeanPurchasing(147,267,1)
plotMeanPurchasing(268,388,1)
plotMeanPurchasing(399,519,1)
plotMeanPurchasing(1356,1236,-1)
plotMeanPurchasing(26,1356,11)
plotMeanPurchasing(26,37,1)
plotMeanPurchasing(1324,1325,1)
plotMeanPurchasing(576,1356,11)


plotMeanMounthlyIncome(26,1356,1)

plotMeanPropCurValue(26,1356,1)

plotMeanPurchasingPromo(26,1356,1)

#plot mean multiple simulation
temp <-data.frame() 
paramMeanCreation(26,1356)
paramMeanCreationPromo(26,1356)

paramMeanCreation(1358,1393)
paramMeanCreationPromo(1358,1393)

paramMeanCreation(1394,1429)
paramMeanCreationPromo(1394,1429)

paramMeanCreation(1430,1433)
paramMeanCreationPromo(1430,1433)





paramMeanCreationPromo<-function(minId,maxId){
  household <- config$nbrhouseholds[minId+1]
  promoter <- config$nbrpromoters[minId+1]
  investor <- config$nbrinvestors[minId+1]
  temps2 = promoterState%>%filter(idsimulation==minId&step==9)
  mean<-mean(temps2$purchasingpower)
  temp<-rbind(temp, c(household, promoter,investor,mean))
  names(temp)[1] <- "household"
  names(temp)[2] <- "promoter"
  names(temp)[3] <- "investor"
  names(temp)[4] <- "mean"
  for (i in seq(minId+2, maxId+1, by =1 )) {
    household <- config$nbrhouseholds[i]
    promoter <- config$nbrpromoters[i]
    investor <- config$nbrinvestors[i]
    temps2 = promoterState%>%filter(idsimulation==i-1&step==9)
    mean<-mean(temps2$purchasingpower)
    temp<-rbind(temp, c(household, promoter,investor,mean))
  }
  
  boxplot(temp$mean~temp$household,main="Promoter Purchasing Power vs nbrHousehold")
  boxplot(temp$mean~temp$investor,main="Promoter Purchasing Power vs nbrInvestor")
  boxplot(temp$mean~temp$promoter,main="Promoter Purchasing Power vs nbrPromoter")
}

paramMeanCreation<-function(minId,maxId){
  household <- config$nbrhouseholds[minId+1]
  promoter <- config$nbrpromoters[minId+1]
  investor <- config$nbrinvestors[minId+1]
  temps2 = householdState%>%filter(idsimulation==minId&step==9)
  mean<-mean(temps2$purchasingpower)
  temp<-rbind(temp, c(household, promoter,investor,mean))
  names(temp)[1] <- "household"
  names(temp)[2] <- "promoter"
  names(temp)[3] <- "investor"
  names(temp)[4] <- "mean"
  
  for (i in seq(minId+2, maxId+1, by =1 )) {
    household <- config$nbrhouseholds[i]
    promoter <- config$nbrpromoters[i]
    investor <- config$nbrinvestors[i]
    temps2 = householdState%>%filter(idsimulation==i-1&step==9)
    mean<-mean(temps2$purchasingpower)
    temp<-rbind(temp, c(household, promoter,investor,mean))
  }
  boxplot(temp$mean~temp$household,main="Household Purchasing Power vs nbrHousehold")
  boxplot(temp$mean~temp$investor,main="Household Purchasing Power vs nbrInvestor")
  boxplot(temp$mean~temp$promoter,main="Household Purchasing Power vs nbrPromoter")
}

plotMeanPurchasing<-function(min,max,step){
  for(i in seq(min, max, by =step )){
    temp = householdState%>%filter(idsimulation==i)
    Meanloop = tapply(temp$purchasingpower,INDEX = temp$step,FUN   = mean)
    if(i==min){
      plot(Meanloop, type="l",ylim=c(-300,700))
    }
    else{
      lines(Meanloop)
    }
  }
}

plotMeanPurchasingPromo<-function(min,max,step){
  for(i in seq(min, max, by =step )){
    temp = promoterState%>%filter(idsimulation==i)
    Meanloop = tapply(temp$purchasingpower,INDEX = temp$step,FUN   = mean)
    if(i==min){
      plot(Meanloop, type="l")
    }
    else{
      lines(Meanloop)
    }
  }
}

plotMeanMounthlyIncome<-function(min,max,step){
  for(i in seq(min, max, by =step )){
    temp = householdState%>%filter(idsimulation==i)
    Meanloop = tapply(temp$netmonthlyincome,INDEX = temp$step,FUN   = mean)
    if(i==min){
      plot(Meanloop, type="l",ylim=c(-300,700))
    }
    else{
      lines(Meanloop)
    }
  }
}

plotMeanPropCurValue<-function(min,max,step){
  for(i in seq(min, max, by =step )){
    temp = propertyState%>%filter(idsimulation==i)
    Meanloop = tapply(temp$currentvalue,INDEX = temp$step,FUN   = mean)
    if(i==min){
      plot(Meanloop, type="l",ylim=c(-300,700))
    }
    else{
      lines(Meanloop)
    }
  }
}


plotSimu<-function(id){
  config <- dbGetQuery(con, "SELECT * from configuration_state")
  
  SQL <-paste("SELECT * FROM red_primaria r WHERE r.gid IN",gsub('\\]',')',gsub('\\[', '(', config$listofnetwork[id+1])))
  
  transport <- get_postgis_query(con, SQL,geom_name = "geom")
  all_transport <- get_postgis_query(con, "SELECT * FROM red_primaria",geom_name = "geom")
  
  SQL =paste("SELECT * FROM equipamentos r WHERE r.codigo_upz IN",gsub('\\]',')',gsub('\\[', '(', config$listofequipement[id+1])), "AND geom!=''  AND codigo_upz != 0 AND codigo_upz != 999")
  
  equipement <- get_postgis_query(con, SQL,geom_name = "geom")
  equipement <- as(equipement,"data.frame")
  
  all_equipement <- get_postgis_query(con, "SELECT * FROM equipamentos WHERE geom!='' AND codigo_upz != 0 AND codigo_upz != 999 ",geom_name = "geom")
  all_equipement<-as(all_equipement,"data.frame")
  
  SQL =paste("SELECT * FROM ((SELECT gid, geom from land) s INNER JOIN property_state p ON p.idland = s.gid) j WHERE j.idSimulation =",config$idsimulation[id+1], "AND j.step=",config$num[id+1]-1)
  
  property <- get_postgis_query(con, SQL,geom_name = "geom")
  property<-as(property,"data.frame")
  
  
  ggplot() + geom_path(aes(x = long, y = lat,group=group),data = all_transport)+ 
    geom_point(aes(x = coords.x1, y = coords.x2,color="red"),data = equipement) + 
    geom_path(aes(x = long, y = lat,group=group,color = "blue"),data = transport) + 
    geom_point(aes(x = coords.x1, y = coords.x2),data = property)
}

plotSimuNoEquip<-function(id){
  config <- dbGetQuery(con, "SELECT * from configuration_state")
  
  SQL <-paste("SELECT * FROM red_primaria r WHERE r.gid IN",gsub('\\]',')',gsub('\\[', '(', config$listofnetwork[id+1])))
  
  transport <- get_postgis_query(con, SQL,geom_name = "geom")
  all_transport <- get_postgis_query(con, "SELECT * FROM red_primaria",geom_name = "geom")
  
  SQL =paste("SELECT * FROM ((SELECT gid, geom from land) s INNER JOIN property_state p ON p.idland = s.gid) j WHERE j.idSimulation =",config$idsimulation[id+1], "AND j.step=",config$num[nrow(config)]-1)
  
  property <- get_postgis_query(con, SQL,geom_name = "geom")
  property<-as(property,"data.frame")
  
  
  ggplot() + geom_path(aes(x = long, y = lat,group=group),data = all_transport)+ 
    geom_path(aes(x = long, y = lat,group=group,color = "red"),data = transport) + 
    geom_point(aes(x = coords.x1, y = coords.x2),data = property)
}

plotSimuNoEquipTrans<-function(id){
  config <- dbGetQuery(con, "SELECT * from configuration_state")
  
  all_transport <- get_postgis_query(con, "SELECT * FROM red_primaria",geom_name = "geom")
  
  SQL =paste("SELECT * FROM ((SELECT gid, geom from land) s INNER JOIN property_state p ON p.idland = s.gid) j WHERE j.idSimulation =",config$idsimulation[id+1], "AND j.step=",config$num[nrow(config)]-1)
  
  property <- get_postgis_query(con, SQL,geom_name = "geom")
  property<-as(property,"data.frame")
  
  
  ggplot() + geom_path(aes(x = long, y = lat,group=group),data = all_transport)+ 
    geom_point(aes(x = coords.x1, y = coords.x2),data = property)
}



