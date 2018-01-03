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
 * 継承戦略のオプションを定義します。
 *
 * @since Java Persistence 1.0
 */
public enum InheritanceType { 

    /** クラス階層で一つのテーブル。 */
    SINGLE_TABLE, 

    /** 具体的なエンティティクラスごとに一つのテーブル。 */
    TABLE_PER_CLASS, 

    /**
     * サブクラスに固有のフィールドが、親クラスでの共通のフィールドとは別のテーブルにマップされ、
     * サブクラスをインスタンス化するためにテーブル結合が実行される戦略。
     */
    JOINED 
}
