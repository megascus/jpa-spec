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
 * 列挙型のマッピングを定義します。
 * 
 * この列挙型の定数は列挙型の永続化プロパティもしくはフィールドがどのように永続化されるべきかを指定します。
 * Defines mapping for enumerated types.  The constants of this
 * enumerated type specify how a persistent property or
 * field of an enumerated type should be persisted.
 * 
 * @since Java Persistence 1.0
 */
public enum EnumType {
    /** 列挙型の永続化プロパティもしくはフィールドが整数値として永続化される。 */
    ORDINAL,

    /** 列挙型の永続化プロパティもしくはフィールドが文字列として永続化される。 */
    STRING
}
