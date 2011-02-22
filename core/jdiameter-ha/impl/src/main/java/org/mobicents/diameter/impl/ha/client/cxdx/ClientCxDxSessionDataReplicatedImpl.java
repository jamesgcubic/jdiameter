/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and/or its affiliates, and individual
 * contributors as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a full listing
 * of individual contributors.
 * 
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU General Public License, v. 2.0.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License,
 * v. 2.0 along with this distribution; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
package org.mobicents.diameter.impl.ha.client.cxdx;

import org.jboss.cache.Fqn;
import org.jdiameter.api.cxdx.ClientCxDxSession;
import org.jdiameter.client.api.IContainer;
import org.jdiameter.client.impl.app.cxdx.IClientCxDxSessionData;
import org.jdiameter.common.api.app.cxdx.CxDxSessionState;
import org.mobicents.cluster.MobicentsCluster;
import org.mobicents.diameter.impl.ha.common.cxdx.CxDxSessionDataReplicatedImpl;
import org.mobicents.diameter.impl.ha.data.ReplicatedSessionDatasource;

/**
 * 
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
public class ClientCxDxSessionDataReplicatedImpl extends CxDxSessionDataReplicatedImpl implements IClientCxDxSessionData {

  /**
   * @param nodeFqn
   * @param mobicentsCluster
   * @param iface
   */
  public ClientCxDxSessionDataReplicatedImpl(Fqn<?> nodeFqn, MobicentsCluster mobicentsCluster, IContainer container) {
    super(nodeFqn, mobicentsCluster, container);

    if (super.create()) {
      setAppSessionIface(this, ClientCxDxSession.class);
      setCxDxSessionState(CxDxSessionState.IDLE);
    }
  }

  /**
   * @param sessionId
   * @param mobicentsCluster
   * @param iface
   */
  public ClientCxDxSessionDataReplicatedImpl(String sessionId, MobicentsCluster mobicentsCluster, IContainer container) {
    this(Fqn.fromRelativeElements(ReplicatedSessionDatasource.SESSIONS_FQN, sessionId), mobicentsCluster, container);
  }

}
