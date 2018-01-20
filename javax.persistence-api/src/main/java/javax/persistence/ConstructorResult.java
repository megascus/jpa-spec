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

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * SQLクエリーのSELECT句をコンストラクタにマッピングするために{@link SqlResultSetMapping}アノテーションと共に使用します。
 *
 * <p>ターゲットクラスのコンストラクタを適用し、指定されたカラムを引数の値として渡します。
 * 意図されたコンストラクタの引数に対応するすべてのカラムはコンストラクタの引数のリストと同じ順序で<code>ConstructorResult</code>アノテーションの<code>columns</code>要素を使用して指定される必要があります。
 * コンストラクタの結果として返されるエンティティは構築されたオブジェクトの主キーが取得されるかどうかによって新規状態またはデタッチ状態のいずれかになります。
 * 
 * <pre>
 *
 * Example:
 *
 *   Query q = em.createNativeQuery(
 *      "SELECT c.id, c.name, COUNT(o) as orderCount, AVG(o.price) AS avgOrder " +
 *      "FROM Customer c, Orders o " +
 *      "WHERE o.cid = c.id " +
 *      "GROUP BY c.id, c.name",
 *      "CustomerDetailsResult");
 *
 *   &#064;SqlResultSetMapping(
 *       name="CustomerDetailsResult",
 *       classes={
 *          &#064;ConstructorResult(
 *               targetClass=com.acme.CustomerDetails.class,
 *                 columns={
 *                    &#064;ColumnResult(name="id"),
 *                    &#064;ColumnResult(name="name"),
 *                    &#064;ColumnResult(name="orderCount"),
 *                    &#064;ColumnResult(name="avgOrder", type=Double.class)
 *                    }
 *          )
 *       }
 *      )
 *
 * </pre>
 *
 * @see SqlResultSetMapping
 * @see ColumnResult
 *
 * @since Java Persistence 2.1
 */
@Target({}) 
@Retention(RUNTIME)

public @interface ConstructorResult { 

    /** (必須) コンストラクタが呼び出されるクラス。 */
    Class targetClass();

    /** 
     *  (必須) SELECTリストのカラムを意図したコンストラクタの引数に順序どおりに行うマッピング。
     */
    ColumnResult[] columns();
}
