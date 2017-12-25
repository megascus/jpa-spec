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
	 * (オプション) この外部キー列によって参照される列の名前。
	 * 
	 * 参照先の列は、ターゲットエンティティのテーブルにあります。
	 * 
	 * (単一の結合カラムが使用された場合のみ適用される)デフォルトは参照先テーブルの主キー列と同じ名前です。
	 */
	String referencedColumnName() default "";

	/**
	 * (オプション) このプロパティがユニークキーかどうか。
	 * 
	 * これはテーブルレベルの<code>UniqueConstraint</code>アノテーションのショートカットであり、
	 * ユニークキー制約が単一のフィールドだけである場合に便利です。
	 */
	boolean unique() default false;

	/**
	 * (オプション) 外部キー列にnullを入れられるかどうか。
	 */
	boolean nullable() default false;

	/**
	 * (オプション) 永続化プロバイダによって生成されるSQL INSERT文にこのカラムを含めるかどうか。
	 */
	boolean insertable() default true;

	/**
	 * (オプション) 永続化プロバイダによって生成されるSQL UPDATE文にこのカラムを含めるかどうか。
	 */
	boolean updatable() default true;

	/**
	 * (オプション) この列が生成されるときのDDLで使用されるSQLフラグメント。
	 * 
	 * デフォルトではSQLはプロバイダによって生成されます。
	 */
	String columnDefinition() default "";

	/**
	 * (オプション) 外部キー列が含まれるテーブルの名前。
	 *  
     * <ul> 
     * <li> 要素コレクションのためのマップキーによる結合の場合、外部キー列はマップの値のコレクションテーブルに存在します。
     * <li> ManyToManyのエンティティ関係や結合テーブルを使用したOneToManyのエンティティ関係の結合の場合、外部キーカラムは結合テーブルに存在します。
     * <li> 外部キーマッピング戦略を使用したOneToManyのエンティティ関係の結合の場合、マップキーのための外部キー列は(Mapの)値がマッピングされたテーブルに存在します。
     * </ul>
     * <p> デフォルト: 
     * <ul>
     * <li> 要素コレクションのためのマップの場合、マップの値のためのコレクションテーブルの名前。
     * <li> マップが結合テーブルが使用されるOneToManyやManyToManyのエンティティ関係の場合、マップのための結合テーブルの名前。 
     * <li> 外部キーマッピング戦略を使用したOneToManyのエンティティ関係のマップの場合、マップの値のエンティティのプライマリーテーブルの名前。
     * </ul>
	 */
	String table() default "";

    /**
     *  (オプション) 表の生成が有効な場合に主キー結合列の外部キー制約の生成を指定または制御するために使用されます。
     * 
     * この要素が指定されていない場合は永続性プロバイダのデフォルトの外部キー方式が適用されます。
     *
     *  @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);
}
