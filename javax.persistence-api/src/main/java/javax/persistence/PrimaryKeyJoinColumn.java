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
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * 他のテーブルに結合するための外部キーとして使用される主キーカラムを指定します。
 *
 * <p>これは{@link InheritanceType#JOINED JOINED}マッピングストラテジーのエンティティのサブクラスのプライマリテーブルを、
 * そのスーパークラスのプライマリテーブルに結合するために使用されます。
 * もしくはセカンダリテーブルをプライマリテーブルに結合するために{@link SecondaryTable}アノテーション内で使用されます。
 * もしくは参照元エンティティの主キーが参照先エンティティへの外部キーとして使用される{@link OneToOne}マッピングで使用できます。
 *
 * <p><code>JOINED</code>マッピングストラテジーのエンティティのサブクラスに<code>PrimaryKeyJoinColumn</code>アノテーションが指定されていない場合、
 * サブクラスの外部キー列はスーパークラスのプライマリテーブルの主キー列と同じ名前を持つとみなされます。 
 *
 * <pre>
 *
 *    Example: Customer and ValuedCustomer subclass
 *
 *    &#064;Entity
 *    &#064;Table(name="CUST")
 *    &#064;Inheritance(strategy=JOINED)
 *    &#064;DiscriminatorValue("CUST")
 *    public class Customer { ... }
 *    
 *    &#064;Entity
 *    &#064;Table(name="VCUST")
 *    &#064;DiscriminatorValue("VCUST")
 *    &#064;PrimaryKeyJoinColumn(name="CUST_ID")
 *    public class ValuedCustomer extends Customer { ... }
 * </pre>
 *
 * @see SecondaryTable
 * @see Inheritance
 * @see OneToOne
 * @see ForeignKey
 *
 * @since Java Persistence 1.0
 */
@Repeatable(PrimaryKeyJoinColumns.class)
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)

public @interface PrimaryKeyJoinColumn {

    /** 
     * (オプション) 現在のテーブルの主キー列の名前。
     * 
     * <p>デフォルトでは(<code>JOINED</code>マッピングストラテジーの場合は)スーパークラスのプライマリテーブルの主キー列と同じ名前になります。
     * または(SecondaryTableマッピングの場合は)プライマリテーブルの主キー列と同じ名前になります。
     * または(OneToOneマッピングの場合は)参照元エンティティのテーブルの主キー列と同じ名前になります。
     */
    String name() default "";

    /** 
     * (オプション) 結合先のテーブルの主キー列の名前。
     * 
     * <p>デフォルトでは(<code>JOINED</code>マッピングストラテジーの場合は)スーパークラスのプライマリテーブルの主キー列と同じ名前になります。
     * または(SecondaryTableマッピングの場合は)プライマリテーブルの主キー列と同じ名前になります。
     * または(OneToOneマッピングの場合は)参照元エンティティのテーブルの主キー列と同じ名前になります。
     */
    String referencedColumnName() default "";

    /**
     * (オプション) カラムのDDLを生成するときに使用されるSQLフラグメント。
     * 
     * これは<code>OneToOne</code>の主キーの関連付けでは使用しないでください。
     * 
     * <p>デフォルトではカラムを推測された型で作成するために生成されたSQLです。
     */
    String columnDefinition() default "";

    /**
     *  (オプション) テーブルの生成が有効な場合に主キー結合列の外部キー制約の生成を指定または制御するために使用されます。
     * 
     * この要素が指定されていない場合は永続化プロバイダのデフォルトの外部キー方式が適用されます。
     *
     *  @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);
}
