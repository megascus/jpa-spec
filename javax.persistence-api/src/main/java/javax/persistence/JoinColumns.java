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
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * 複合外部キーのマッピングを指定します。
 * 
 * このアノテーションは同じ関係の<code>JoinColumn</code>アノテーションをまとめます。
 *
 * <p> <code>JoinColumns</code>アノテーションが使用されるときは
 * <code>JoinColumn</code>アノテーションのすべてで<code>name</code>要素と<code>referencedColumnName</code>要素の両方を指定する必要があります。
 *
 * <pre>
 *
 *    Example:
 *    &#064;ManyToOne
 *    &#064;JoinColumns({
 *        &#064;JoinColumn(name="ADDR_ID", referencedColumnName="ID"),
 *        &#064;JoinColumn(name="ADDR_ZIP", referencedColumnName="ZIP")
 *    })
 *    public Address getAddress() { return address; }
 * </pre>
 *
 * @see JoinColumn
 * @see ForeignKey
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface JoinColumns {

    /**
     * この関係をマッピングする結合列。
     */
    JoinColumn[] value();

    /**
     * (オプション) 表の生成が有効な場合に外部キー制約の生成を指定または制御するために使用されます。
     * 
     * この要素と<code>joinColumn</code>要素のいずれかの<code>foreignKey</code>要素の両方が指定されていた場合の挙動は未定義です。
     * いずれの場所にも外部キーのアノテーション要素が指定されていない場合は、永続化プロバイダーのデフォルトの外部キー戦略が適用されます。
     *
     *  @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);
}
