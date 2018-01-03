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
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * エンティティの関連や要素コレクションを結合する列を指定します。
 * 
 * <code>JoinColumn</code>アノテーション自身がデフォルトの場合、単一の結合列が想定され、デフォルト値が適用されます。
 *
 * <pre>
 *   Example:
 *
 *   &#064;ManyToOne
 *   &#064;JoinColumn(name="ADDR_ID")
 *   public Address getAddress() { return address; }
 *
 *
 *   Example: 外部キーマッピングを使用した単方向の1対多の関連
 * 
 *   // In Customer class
 *   &#064;OneToMany
 *   &#064;JoinColumn(name="CUST_ID") // join column is in table for Order
 *   public Set&#060;Order&#062; getOrders() {return orders;}
 * </pre>
 *
 * @see ManyToOne
 * @see OneToMany
 * @see OneToOne
 * @see JoinTable
 * @see CollectionTable
 * @see ForeignKey
 *
 * @since Java Persistence 1.0
 */
@Repeatable(JoinColumns.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface JoinColumn {

    /** 
     * (オプション) The name of the foreign key column.
     * The table in which it is found depends upon the
     * context. 
     * <ul>
     * <li>If the join is for a OneToOne or ManyToOne
     *  mapping using a foreign key mapping strategy, 
     * the foreign key column is in the table of the
     * source entity or embeddable. 
     * <li> If the join is for a unidirectional OneToMany mapping
     * using a foreign key mapping strategy, the foreign key is in the
     * table of the target entity.  
     * <li> If the join is for a ManyToMany mapping or for a OneToOne
     * or bidirectional ManyToOne/OneToMany mapping using a join
     * table, the foreign key is in a join table.  
     * <li> If the join is for an element collection, the foreign 
     * key is in a collection table.
     *</ul>
     *
     * <p> Default (only applies if a single join column is used):
     * The concatenation of the following: the name of the 
     * referencing relationship property or field of the referencing 
     * entity or embeddable class; "_"; the name of the referenced 
     * primary key column. 
     * If there is no such referencing relationship property or 
     * field in the entity, or if the join is for an element collection,
     * the join column name is formed as the 
     * concatenation of the following: the name of the entity; "_"; 
     * the name of the referenced primary key column.
     */
    String name() default "";

    /**
     * (オプション) この外部キー列から参照される列の名前。
     * <ul>
     * <li> When used with entity relationship mappings other
     * than the cases described here, the referenced column is in the
     * table of the target entity. 
     * <li> When used with a unidirectional OneToMany foreign key
     * mapping, the referenced column is in the table of the source
     * entity.  
     * <li> When used inside a <code>JoinTable</code> annotation,
     * the referenced key column is in the entity table of the owning
     * entity, or inverse entity if the join is part of the inverse
     * join definition.  
     * <li> When used in a <code>CollectionTable</code> mapping, the
     * referenced column is in the table of the entity containing the
     * collection.
     * </ul>
     *
     * <p> デフォルト (単一の結合列が使用された場合のみ適用される): 
     * 参照先のテーブルの主キー列と同じ名前。
     */
    String referencedColumnName() default "";

    /**
     * (オプション) このプロパティがユニークキーかどうか。
     * 
	 * これはテーブルレベルの<code>UniqueConstraint</code>アノテーションのショートカットであり、
	 * ユニークキー制約が単一のフィールドだけである場合に便利です。
     * 外部キーの一部である主キーに対応する結合列に対して明示的に指定する必要はありません。
     */
    boolean unique() default false;

    /** (オプション) 外部キーのカラムがNULLを許容するかどうか。 */
    boolean nullable() default true;

    /**
     * (オプション) 永続化プロバイダによって生成されたSQL INSERTステートメントにカラムが含まれるかどうか。
     */
    boolean insertable() default true;

    /**
     * (オプション) 永続化プロバイダによって生成されたSQL UPDATEステートメントにカラムが含まれるかどうか。
     */
    boolean updatable() default true;

    /**
	 * (オプション) カラムのDDLを生成するときに使用されるSQLフラグメント。
	 * 
	 * デフォルトでは型推論されたカラムを作成するSQLを生成します。
     */
    String columnDefinition() default "";

    /**
     * (オプション) この列を含めるテーブルの名前。
     * 
     * テーブルが指定されない場合、列は該当するエンティティのプライマリーテーブルにあるとみなされます。
     *
     * <p> デフォルト: 
     * <ul>
     * <li> 結合が外部キーマッピング戦略を使用するOneToOneまたはManyToOneマッピングの場合、
     * ソースエンティティまたは組み込みクラスのテーブルの名前。
     * <li> 結合が外部キーマッピング方式を使用する単方向OneToManyマッピングの場合、ターゲットエンティティーの表の名前。
     * <li> 結合がmanyToManyマッピングの場合や結合テーブルを使用するOneToOneまたは双方向のManyToOneやOneToManyマッピングの場合、
     * 結合テーブルの名前。
     * <li> 結合が要素コレクションの場合、コレクションテーブルの名前。
     * </ul>
     */
    String table() default "";

    /**
     *  (オプション) テーブルの生成が有効なときに外部キー制約の生成を指定または制御するために使用されます。
     * 
     *  この要素が指定されていない場合、永続化プロバイダのデフォルトの外部キー方式が適用されます。
     *
     *  @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);
}
