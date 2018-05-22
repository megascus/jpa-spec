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
 * エンティティの関連や要素コレクションを結合するカラムを指定します。
 * 
 * <code>JoinColumn</code>アノテーション自身がデフォルトの場合、単一の結合カラムが想定され、デフォルト値が適用されます。
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
     * (オプション) 外部キーのカラムの名前。
     * 
     * 見つかるテーブルはコンテキストに依存します。
     * <ul>
     * <li> 結合が外部キーマッピング戦略を使用するOneToOneまたはManyToOneマッピングの場合、
     * 外部キーのカラムはソースエンティティまたは組み込みクラスのテーブルにあります。
     * <li> 結合が外部キーマッピング方式を使用する単方向OneToManyマッピングの場合、
     * 外部キーはターゲットエンティティのテーブルにあります。
     * <li> 結合がManyToManyマッピング、または結合テーブルを使用するOneToOneまたは双方向ManyToOne、OneToManyマッピングの場合、
     * 外部キーは結合テーブルにあります。
     * <li> 結合が要素コレクションの場合、外部キーはコレクションテーブルにあります。
     *</ul>
     *
     * <p> デフォルト(単一の結合カラムが使用された場合のみ適用される): 
     * 参照のリレーションシップのプロパティの名前または参照元のエンティティまたは埋め込み可能クラスのフィールド + "_" + 参照される主キーのカラムの名前
     * <br>
     * エンティティにそのような参照のリレーションシップのプロパティまたはフィールドがない場合、もしくは要素コレクションの結合である場合、結合カラム名は次のようになります。 
     * エンティティの名前 + "_" + 参照される主キーのカラムの名前
     */
    String name() default "";

    /**
     * 
     * (オプション) この外部キーのカラムから参照されるカラムの名前。
     * <ul>
     * <li> ここで説明するケース以外のエンティティのリレーションシップのマッピングで使用する場合、参照先のカラムはターゲットエンティティのテーブルにあります。
     * <li> 単方向のOneToMany外部キーマッピングとともに使用する場合、参照されるカラムはソースエンティティのテーブル内にあります。
     * <li> <code>JoinTable</code>アノテーション内で使用される場合、参照されるキーのカラムは所有側のエンティティのテーブルにあり、
     * 逆側の結合の定義の一部である場合は逆側のエンティティのテーブルにあります。
     * <li> <code>CollectionTable</code>マッピングで使用される場合、参照されるカラムはコレクションを含むエンティティのテーブルにあります。
     * </ul>
     *
     * <p> デフォルト (単一の結合カラムが使用された場合のみ適用される): 
     * 参照先のテーブルの主キーのカラムと同じ名前。
     */
    String referencedColumnName() default "";

    /**
     * (オプション) このプロパティがユニークキーかどうか。
     * 
	 * これはテーブルレベルの<code>UniqueConstraint</code>アノテーションのショートカットであり、
	 * ユニークキー制約が単一のフィールドだけである場合に便利です。
     * 外部キーの一部である主キーに対応する結合カラムに対して明示的に指定する必要はありません。
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
     * (オプション) このカラムを含めるテーブルの名前。
     * 
     * テーブルが指定されない場合、カラムは該当するエンティティのプライマリーテーブルにあるとみなされます。
     *
     * <p> デフォルト: 
     * <ul>
     * <li> 結合が外部キーマッピング戦略を使用するOneToOneまたはManyToOneマッピングの場合、
     * ソースエンティティまたは組み込みクラスのテーブルの名前。
     * <li> 結合が外部キーマッピング方式を使用する単方向OneToManyマッピングの場合、ターゲットエンティティのテーブルの名前。
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
