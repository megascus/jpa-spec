/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

/**
 * <code>java.util.Date</code>または<code>java.util.Calendar</code>への特別なマッピングを示すために使用される型です。
 * 
 * <p>訳注：Java 8から増えたDate and Time APIのLocalDate等では使用する必要はありません。
 *
 * @since Java Persistence 1.0
 */
public enum TemporalType {

    /** <code>java.sql.Date</code>としてマップします。 */
    DATE, 

    /** <code>java.sql.Time</code>としてマップします。 */
    TIME, 

    /** <code>java.sql.Timestamp</code>としてマップします。 */
    TIMESTAMP
}
