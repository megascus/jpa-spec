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
 * SQLクエリーのSELECTリストで指定されたカラムをエンティティクラスのプロパティーやフィールドにマッピングするために
 * {@link EntityResult}アノテーションと一緒に使用します。
 *
 * <pre>
 *
 * Example:
 *   Query q = em.createNativeQuery(
 *       "SELECT o.id AS order_id, " +
 *           "o.quantity AS order_quantity, " +
 *           "o.item AS order_item, " +
 *         "FROM Order o, Item i " +
 *         "WHERE (order_quantity &gt; 25) AND (order_item = i.id)",
 *       "OrderResults");
 *
 *   &#064;SqlResultSetMapping(name="OrderResults",
 *       entities={
 *           &#064;EntityResult(entityClass=com.acme.Order.class, fields={
 *               &#064;FieldResult(name="id", column="order_id"),
 *               &#064;FieldResult(name="quantity", column="order_quantity"),
 *               &#064;FieldResult(name="item", column="order_item")})
 *       })
 * </pre>
 *
 * @see EntityResult
 * @see SqlResultSetMapping
 * @since Java Persistence 1.0
 */
@Target({}) 
@Retention(RUNTIME)

public @interface FieldResult { 

    /** クラスの永続化フィールドもしくはプロパティの名前。 */
    String name();

    /** 
     * SELECT句のカラムの名前。該当する場合はカラムの別名。
     */
    String column();
}
