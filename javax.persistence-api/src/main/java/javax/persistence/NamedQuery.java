/*******************************************************************************
 * Copyright (c) 2008 - 2015 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Petros Splinakis - Java Persistence 2.2
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static javax.persistence.LockModeType.NONE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** 
 * JPQL(Java Persistence query language)の名前付きクエリーを静的に指定します。
 * クエリーの名前は永続化ユニット内で共有されます。
 *  <p><code>NamedQuery</code>アノテーションはエンティティ({@link Entity}の付いたクラス)やマップドスーパークラス({@link MappedSuperclass}の付いたクラス)に適用できます。
 *
 * <p> 以下はJPQLの名前付きクエリーの定義の例です。
 *
 * <pre>
 *    &#064;NamedQuery(
 *            name="findAllCustomersWithName",
 *            query="SELECT c FROM Customer c WHERE c.name LIKE :custName"
 *    )
 * </pre>
 *
 * <p> 以下は名前付きクエリーの使用方法の例です。
 *
 * <pre>
 *    &#064;PersistenceContext
 *    public EntityManager em;
 *    ...
 *    customers = em.createNamedQuery("findAllCustomersWithName")
 *            .setParameter("custName", "Smith")
 *            .getResultList();
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Repeatable(NamedQueries.class)
@Target({TYPE}) 
@Retention(RUNTIME)
public @interface NamedQuery {

    /** 
     * (必須) クエリーオブジェクトを作成する {@link EntityManager}のメソッドでクエリを参照するために使用される名前。
     */
    String name();

    /** 
     * (必須) JPQLのクエリー文字列。
     */
    String query();

    /** 
     * (オプション) クエリー実行で使用されるロックモードタイプ。
     * <code>lockMode</code>に<code>LockModeType.NONE</code>以外が指定されている場合、クエリーはトランザクション内で実行されなければならず、
     * 永続化コンテキストはトランザクションに参加する必要があります。
     * @since Java Persistence 2.0
     */
    LockModeType lockMode() default NONE;
    
    /**
     * (オプション) クエリープロパティとヒント。ベンダー固有のヒントを含めることができます。
     */
    QueryHint[] hints() default {};
}
