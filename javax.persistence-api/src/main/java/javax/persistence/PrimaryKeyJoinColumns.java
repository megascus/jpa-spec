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
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * {@link PrimaryKeyJoinColumn}アノテーションをまとめます。
 * 
 * 複合外部キーをマップするために使用されます。
 *
 * <pre>
 *    Example: ValuedCustomer subclass
 *
 *    &#064;Entity
 *    &#064;Table(name="VCUST")
 *    &#064;DiscriminatorValue("VCUST")
 *    &#064;PrimaryKeyJoinColumns({
 *        &#064;PrimaryKeyJoinColumn(name="CUST_ID", 
 *            referencedColumnName="ID"),
 *        &#064;PrimaryKeyJoinColumn(name="CUST_TYPE",
 *            referencedColumnName="TYPE")
 *    })
 *    public class ValuedCustomer extends Customer { ... }
 * </pre>
 *
 * @see ForeignKey
 *
 * @since Java Persistence 1.0
 */
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)

public @interface PrimaryKeyJoinColumns {

    /** 1つ以上の<code>PrimaryKeyJoinColumn</code>アノテーション。 */
    PrimaryKeyJoinColumn[] value();

    /**
     *  (オプション) テーブルの生成が有効なときに外部キー制約の生成を指定または制御するために使用されます。
     * 
     *  この要素と<code>PrimaryKeyJoinColumn</code>要素内のいずれかのforeignKey要素の両方が指定されている場合の動作は未定義です。
     *  どの場所でも外部キー注釈要素が指定されていない場合、永続化プロバイダのデフォルトの外部キー方式が適用されます。
     *
     *  @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);

}
