/*******************************************************************************
 * Copyright (c) 2004 Vlad Dumitrescu and others. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse Public
 * License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Vlad Dumitrescu
 *******************************************************************************/
package org.erlide.runtime.shell;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

import org.erlide.util.ErlLogger;
import org.erlide.util.erlang.OtpBindings;
import org.erlide.util.erlang.OtpErlang;

import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.google.common.base.Charsets;

public class IoRequest {

    public static final Pattern RE_PROMPT = Pattern
            .compile("\\([^)]+\\)[0-9]+> |[0-9]+> ");
    public static final Pattern RE_HEADER = Pattern
            .compile("Eshell V[0-9]+\\.[0-9]+\\.[0-9]+");

    public enum IoRequestKind {
        HEADER, PROMPT, INPUT, OUTPUT, STDOUT, STDERR
    }

    private OtpErlangPid leader;
    private OtpErlangPid sender;
    private String message;
    private int start;
    private final IoRequestKind kind;
    private Charset encoding;

    public IoRequest(final OtpErlangTuple obj) {
        try {
            OtpBindings b = OtpErlang.match("{Payload, Leader, From, Tstamp}", obj);
            if (b == null) {
                b = OtpErlang.match("{Payload, Encoding, Leader, From, Tstamp}", obj);
                encoding = getEncoding(b.getAtom("Encoding"));
            } else {
                encoding = Charsets.ISO_8859_1;
            }

            final OtpErlangObject o = b.get("Payload");
            if (o instanceof OtpErlangString) {
                message = ((OtpErlangString) o).stringValue();
            } else if (o instanceof OtpErlangList) {
                final OtpErlangList l = (OtpErlangList) o;
                if (l.arity() == 0) {
                    message = "";
                } else {
                    try {
                        message = l.stringValue();
                    } catch (final Exception e) {
                        message = o.toString();
                    }
                }
            } else {
                message = o.toString();
            }
            message = convertEncoding(message, encoding);

            leader = b.getPid("Leader");
            final OtpErlangObject s = b.get("From");
            if (s instanceof OtpErlangPid) {
                sender = (OtpErlangPid) s;
            } else {
                sender = new OtpErlangPid("s", 0, 0, 0);
            }
        } catch (final Exception e) {
            ErlLogger.warn(e);
            message = "";
        }
        if (IoRequest.RE_PROMPT.matcher(message).matches()) {
            kind = IoRequestKind.PROMPT;
        } else if (IoRequest.RE_HEADER.matcher(message).matches()) {
            kind = IoRequestKind.HEADER;
        } else {
            kind = IoRequestKind.OUTPUT;
        }
    }

    private String convertEncoding(final String message2, final Charset encoding2) {
        return new String(message2.getBytes(Charsets.ISO_8859_1), encoding2);
    }

    private Charset getEncoding(final String atom) {
        if ("unicode".equals(atom)) {
            return Charsets.UTF_8;
        }
        return Charsets.ISO_8859_1;
    }

    public IoRequest(final String msg, final IoRequestKind kind) {
        message = msg;
        encoding = Charsets.ISO_8859_1;
        leader = new OtpErlangPid("s", 0, 0, 0);
        sender = new OtpErlangPid("s", 0, 0, 0);
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "{" + kind.toString() + ":: '" + message + "'@" + encoding + ", " + start
                + "/" + message.length() + ", " + leader + ", " + sender + "}";
    }

    public OtpErlangPid getLeader() {
        return leader;
    }

    public String getMessage() {
        return message;
    }

    public OtpErlangPid getSender() {
        return sender;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return message.length();
    }

    public IoRequestKind getKind() {
        return kind;
    }

    public void setStart(final int pos) {
        start = pos;
    }
}
