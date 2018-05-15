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
package javax.persistence.criteria;

/**
 * クエリーの結果の順序付けを定義するオブジェクトです。
 *
 * @since Java Persistence 2.0
 */
public interface Order {

   /**
    * 順序を入れ替えます。
    * @return 順序を反転させた新しい<code>Order</code>インスタンス
    */
    Order reverse();

   /**
    * 昇順が有効かどうか。
    * @return 昇順かどうかを示すブール式
    */
    boolean isAscending();

   /**
    * 順序付けに使用される式を返します。
    * @return 順序付けに使用される式
    */
   Expression<?> getExpression();
}
