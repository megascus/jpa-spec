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
 * マップキーのエンティティのマッピングを指定します。
 * 
 * マップキー結合列はマップで表される結合が使用されるターゲットエンティティのコレクションテーブルや、結合テーブル、テーブル内にあります。
 * <code>MapKeyJoinColumn</code>が指定されていない場合は単一の結合列が使用され、デフォルト値が適用されます。
 *
 * <pre>
 *
 *    Example 1:
 *
 *    &#064;Entity
 *    public class Company {
 *       &#064;Id int id;
 *       ...
 *       &#064;OneToMany   // 単方向
 *       &#064;JoinTable(name="COMPANY_ORGANIZATION",
 *                  joinColumns=&#064;JoinColumn(name="COMPANY"),
 *                  inverseJoinColumns=&#064;JoinColumn(name="VICEPRESIDENT"))
 *       &#064;MapKeyJoinColumn(name="DIVISION")
 *       Map&#060;Division, VicePresident&#062; organization;
 *    }
 *
 *    Example 2:
 *
 *    &#064;Entity
 *    public class VideoStore {
 *       &#064;Id int id;
 *       String name;
 *       Address location;
 *       ...
 *       &#064;ElementCollection
 *       &#064;CollectionTable(name="INVENTORY",
 *                        joinColumns=&#064;JoinColumn(name="STORE"))
 *       &#064;Column(name="COPIES_IN_STOCK")
 *       &#064;MapKeyJoinColumn(name="MOVIE", referencedColumnName="ID")
 *       Map&#060;Movie, Integer&#062; videoInventory;
 *       ...
 *     }
 *
 *     &#064;Entity
 *     public class Movie {
 *        &#064;Id long id;
 *        String title;
 *        ...
 *     }
 *
 *     Example 3:
 *
 *     &#064;Entity
 *     public class Student {
 *        &#064;Id int studentId;
 *        ...
 *        &#064;ManyToMany  // studentsとcoursesはそれぞれmany-many
 *        &#064;JoinTable(name="ENROLLMENTS",
 *                   joinColumns=&#064;JoinColumn(name="STUDENT"),
 *                   inverseJoinColumns=&#064;JoinColumn(name="SEMESTER"))
 *        &#064;MapKeyJoinColumn(name="COURSE")
 *        Map&#060;Course, Semester&#062;  enrollment;
 *        ...
 *     }
 * </pre>
 *
 * @see ForeignKey
 *
 * @since Java Persistence 2.0
 */
@Repeatable(MapKeyJoinColumns.class)
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface MapKeyJoinColumn {
	/**
	 * (オプション) マップキーのための外部キー列の名前。
	 * どのテーブルで見つけるかはコンテキストに依存します。  
     * <ul> 
     * <li> 要素コレクションのためのマップキーによる結合の場合、外部キー列はマップの値のコレクションテーブルに存在します。
     * <li> ManyToManyのエンティティ関係や結合テーブルを使用したOneToManyのエンティティ関係の結合の場合、外部キーカラムは結合テーブルに存在します。
     * <li> 外部キーマッピング戦略を使用したOneToManyのエンティティ関係の結合の場合、マップキーのための外部キー列は(Mapの)値がマッピングされたテーブルに存在します。
     * </ul>
     *
     * <p> (単一の結合カラムが使用された場合のみ適用される)デフォルトのカラム名は次のものを連結します。
	 * ：参照するエンティティや組み込みクラスの参照する関係プロパティやフィールド + "_" + "KEY"
	 */
	String name() default "";

	/**
	 * (オプション) The name of the column referenced by this foreign key column.
	 * The referenced column is in the table of the target entity.
     *
     * <p> Default (only applies if single join column is being
     * used.) The same name as the primary key column of the
     * referenced table
	 */
	String referencedColumnName() default "";

	/**
	 * (オプション) Whether the property is a unique key. This is a
	 * shortcut for the <code>UniqueConstraint</code> annotation
	 * at the table level and is useful for when the unique key
	 * constraint is only a single field.
	 */
	boolean unique() default false;

	/**
	 * (オプション) Whether the foreign key column is nullable.
	 */
	boolean nullable() default false;

	/**
	 * (オプション) Whether the column is included in SQL INSERT statements
	 * generated by the persistence provider.
	 */
	boolean insertable() default true;

	/**
	 * (オプション) Whether the column is included in SQL UPDATE statements
	 * generated by the persistence provider.
	 */
	boolean updatable() default true;

	/**
	 * (オプション) The SQL fragment that is used when generating the DDL for the
	 * column.
     *  Defaults to SQL generated by the provider for the column.
	 */
	String columnDefinition() default "";

	/**
	 * (オプション) The name of the table that contains the foreign key column. 
     * <ul>
     * <li> If the join is for a map key for an element collection, the foreign key
     * column is in the collection table for the map value. 
     * <li> If the join is for a map key for a ManyToMany entity relationship 
     * or for a OneToMany entity relationship using a join table, 
     * the foreign key column is in a join table.
	 * <li> If the join is for a OneToMany entity relationship using a foreign
	 * key mapping strategy, the foreign key column for the map key is in the
	 * table of the entity that is the value of the map.
     * </ul>
     * <p> Default: 
     * <ul>
     * <li> If the map is for an element collection, the
     * name of the collection table for the map value.
     * <li> If the map is for a OneToMany or ManyToMany entity relationship 
     * using a join table, the name of the join table for the map. 
     * <li> If the map is for a OneToMany entity relationship using a
     * foreign key mapping strategy, the name of the primary table
     * of the entity that is the value of the map.
     * </ul>
	 */
	String table() default "";

    /**
     *  (オプション) Used to specify or control the generation of a
     *  foreign key constraint when table generation is in effect.  If
     *  this element is not specified, the persistence provider's
     *  default foreign key strategy will apply.
     *
     *  @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);
}
