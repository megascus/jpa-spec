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
 * SQLクエリーのSELECT句をエンティティの結果にマッピングするために{@link SqlResultSetMapping}アノテーションとともに使用します。
 *
 * <p>このアノテーションを使用する場合、SQL文はエンティティオブジェクトにマッピングされているすべての列を選択する必要があります。
 * 関連するエンティティへの外部キー列を含める必要があります。
 * データが不十分である場合に得られる結果は未定義です。
 *
 * <pre>
 *   Example:
 *
 *   Query q = em.createNativeQuery(
 *       "SELECT o.id, o.quantity, o.item, i.id, i.name, i.description "+
 *           "FROM Order o, Item i " +
 *           "WHERE (o.quantity &gt; 25) AND (o.item = i.id)",
 *       "OrderItemResults");
 *   &#064;SqlResultSetMapping(name="OrderItemResults",
 *       entities={
 *           &#064;EntityResult(entityClass=com.acme.Order.class),
 *           &#064;EntityResult(entityClass=com.acme.Item.class)
 *   })
 * </pre>
 *
 * @see SqlResultSetMapping
 *
 * @since Java Persistence 1.0
 */
@Target({}) 
@Retention(RUNTIME)
public @interface EntityResult { 

    /** 結果のクラス。 */
    Class entityClass(); 

    /** 
     * クエリーのSELECTリストで指定された列をエンティティクラスのプロパティまたはフィールドにマップします。
     */
    FieldResult[] fields() default {};

    /** 
     * エンティティインスタンスの型を決定するために使用されるSELECTリスト内の列の列名(もしくはエイリアス)を指定します。
     */
    String discriminatorColumn() default "";
}
