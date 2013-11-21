/*
 * Software License Agreement (BSD License)
 *
 * Copyright (c) 2011, Willow Garage, Inc.
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 *  * Neither the name of Willow Garage, Inc. nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *   
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.github.rosjava.android_apps.application_management;

import java.util.Date;

public class ConcertDescription extends MasterDescription implements java.io.Serializable {
    private String description;
    private String[] userRoles;
    private int currentRole = -1;

    public static ConcertDescription createUnknown(MasterId masterId) {
        return new ConcertDescription(masterId, NAME_UNKNOWN, null, null, new Date());
    }

    public ConcertDescription(MasterId masterId, String concertName, String description,
                              rocon_std_msgs.Icon concertIcon, Date timeLastSeen) {
        super(masterId, concertName, "Rocon concert", concertIcon, null, timeLastSeen);

        this.description = description;

        // no gatewayName on concerts
    }

    public void copyFrom(ConcertDescription other) {
            super.copyFrom(other);

            userRoles = other.userRoles.clone();
    }

    public String[] getUserRoles()  {
        return userRoles;
    }

    public String getCurrentRole()  {
        if (userRoles != null && currentRole >= 0 && currentRole <  userRoles.length)
            return userRoles[currentRole];
        else
            return null;
    }

    public void setUserRoles(concert_msgs.Roles roles)
    {
        java.util.List<String> tmp = roles.getList();
        userRoles = new String[tmp.size()];
        tmp.toArray(userRoles);
    }
    public void setCurrentRole(int role) {
        currentRole = role;
    }
}