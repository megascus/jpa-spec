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
 * 識別カラムでサポートされている型を定義します。
 *
 * @since Java Persistence 1.0
 */
public enum DiscriminatorType { 

    /** 
     * 識別型として文字列。
     */
    STRING,

    /** 
     * 識別型として文字。
     */
    CHAR,

    /** 
     * 識別型として整数。
     */
    INTEGER
}
