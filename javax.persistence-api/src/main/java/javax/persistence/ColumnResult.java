/*******************************************************************************
 * Copyright (c) 2008 - 2014 Oracle Corporation. All rights reserved.
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

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * SQLクエリーのSELECTリストの列をマッピングするために{@link SqlResultSetMapping}アノテーションもしくは{@link ConstructorResult}アノテーションと共に使用します。
 * 
 * <p><code>name</code>要素はSELECTリスト内の列の名前(該当する場合は、列のエイリアス)を参照します。
 * このアノテーションをメタデータで指定することでスカラー結果型をクエリーの結果に含めることができます。
 *
 * <pre>
 *
 * Example:
 *   Query q = em.createNativeQuery(
 *       "SELECT o.id AS order_id, " +
 *           "o.quantity AS order_quantity, " +
 *           "o.item AS order_item, " + 
 *           "i.name AS item_name, " +
 *         "FROM Order o, Item i " +
 *         "WHERE (order_quantity &gt; 25) AND (order_item = i.id)",
 *       "OrderResults");
 *
 *   &#064;SqlResultSetMapping(name="OrderResults",
 *       entities={
 *           &#064;EntityResult(entityClass=com.acme.Order.class, fields={
 *               &#064;FieldResult(name="id", column="order_id"),
 *               &#064;FieldResult(name="quantity", column="order_quantity"),
 *               &#064;FieldResult(name="item", column="order_item")})},
 *       columns={
 *           &#064;ColumnResult(name="item_name")}
 *       )
 * </pre>
 *
 * @see SqlResultSetMapping
 *
 * @since Java Persistence 1.0
 */
@Target({}) 
@Retention(RUNTIME)

public @interface ColumnResult { 

    /** (必須) SQLクエリーのSELECT句の中に含まれる列の名前 */
    String name();

    /** 
     *  (オプション) 列の型をマッピングするJavaの型。
     * 
     *  <code>type</code>要素が指定されていない場合は、列のデフォルトのJDBC型マッピングが使用されます。
     *  @since Java Persistence 2.1
     */
    Class type() default void.class;
}
