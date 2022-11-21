/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cbs.amq.client.consumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import javax.jms.JMSException;

import com.cbs.amq.client.constants.Constant;
import com.cbs.amq.client.producer.QueueMessageProducer;


public class Consumer {

   public static void main(final String[] args) throws Exception {
	   InputStream input = QueueMessageProducer.class.getClassLoader().getResourceAsStream("config.properties");
       Properties props = new Properties();
       try {
			props.load(input);
	   } catch (IOException e) {
			e.printStackTrace();
	   }
       
	   String userName = props.getProperty("amq.username");
	   String password = props.getProperty("amq.password");
	   String brokerURL = props.getProperty("amq.broker.url");
	   
	    try (Scanner scanIn = new Scanner(System.in)) {
	           System.out.println("Enter Q name");
	           String qName = scanIn.nextLine();
	           QueueMessageConsumer queueMsgListener = new QueueMessageConsumer(brokerURL,userName,password);

	           queueMsgListener.setDestinationName(qName);

	           try {
	               queueMsgListener.run();

	           } catch (JMSException e) {
	               e.printStackTrace();
	           }
	   }
	   
	   
      
   }
}
