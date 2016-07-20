/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.education.servers;

import com.vng.zing.calc.thrift.Calc;
import com.vng.zing.thriftserver.ThriftServers;

/**
 *
 * @author namnq
 */
public class TServers {

	public boolean setupAndStart() {
		ThriftServers servers = new ThriftServers("education");
//		Calc.Processor processor = new Calc.Processor(new TCalcHandler());
//		servers.setup(processor);
		return servers.start();
	}
}
