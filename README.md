<H1>Лавров Никита РИ-230944</H1>
<h2>Введение</h2>
<h3>Тема проекта:</h3>
<p>Корреляция оценок студентов по темам курса</p>
<h3>Идея:</h3>
<p>В последние годы университеты все чаще внедряют метод обучения по курсам, что поднимает важный вопрос: как оценить эффективность этих курсов?</p>
<p><b>Я выдвинул следующую гипотезу:</b> Если баллы студентов по одной теме курса положительно коррелируют с баллами по следующей теме, это свидетельствует об эффективности курса, так как темы связаны и способствуют обучению.</p>
<h3>Актуальность</h3>
<p>Актуальность данного исследования обусловлена внедрением системы индивидуальных образовательных траекторий (ИОТ) в учебные заведения, где каждый студент выбирает курсы в соответствии со своими интересами и целями. Эта система является относительно новой и требует тщательной оценки, чтобы понять, как она влияет на каждого студента.</p>
<p>Проведенный анализ позволит выявить зависимости между темами, изучаемыми в рамках курса, и даст возможность оценить, насколько эффективно они способствуют обучению. Это, в конечном итоге, поможет в дальнейшем совершенствовании образовательных программ и повышении качества обучения.</p>

<h2>Этапы выполнения</h2>
<h3>1) Создание моделей</h3>
<p>Модели менялись неоднократно почти до самого конца проекта, так как поднимался вопрос о необходимости хранения тех или иных данных. В конечном итоге модели выглядят следующим образом:</p>
![image](https://github.com/user-attachments/assets/d908c00b-5241-41c2-b522-334ae7d227ec)

<h3>2) Парсинг данных</h3>
<p>Были даны 2 файла с оценками, которые необходимо было спарсить. Снова поднимался вопрос о моделях и хранимых данных, потому парсер через время тоже поменялся. Конечный вариант:</p>
![image](https://github.com/user-attachments/assets/fd70c4e7-c991-4f1e-be41-d99eedf5b855)

<h3>3) Работа с API</h3>
<p>Так как для моей задачи нет нужды доставать какую-либо информацию извне, я сделал отдельную программу для определения расстояния родного города студента до УрФУ. Принцип работы: 
<ul>
  <li>Программа находит студента по имени в ВКонтакте и достает родной город, выполняется при помощи VK API</li>
  <li>Определяются координаты родного города студента с помощью библиотеки OpenCage</li>
  <li>При помощи формулы Хаверсина вычисляется дистанция</li>
  <li>Вывод результата</li>
</ul>
![Screenshot 2024-12-24 214319](https://github.com/user-attachments/assets/4c523e59-7340-4331-8c34-dcdf7736349d)
</p>

<h3>4) База данных</h3>
<p>Подключил ORM Hibernate и PostgreSQL к проекту, сделал необходимые методы для взаимодействия с БД, а именно: Сохранить, Получить по имени и Получить все. Создал сущность для хранения в БД, она всего одна, так как в других данных нет нужды.</p>
<h4>4.1) Анализ данных</h4>
<p>Этап, который отдельно никак не обозначен, но основной в рамках темы. Для анализа я определил зависимость, используя корреляционый метод Пирсона (библиотека Apache Commons Math)</p>
![image](https://github.com/user-attachments/assets/fd69066e-502e-47cf-ab29-8505b48e5c02)
<p>В конечном итоге, в сущность я сохранял имя студента и массив с вычисленной корреляцией между каждой темой</p>

<h3>5) Визуализация</h5>
<p>Конечный этап проекта. В нём необходимо было построить графики проделанной работы. Использовал библиотеку JFreeChart, построил 3 графика:
<ul>
  <li>График зависимости</li>
  <li>График зависимости с линией регрессии</li>
  <li>Круговая диаграмма соотношения студентов подходящих под анализ</li>
</ul>
</p>
![image](https://github.com/user-attachments/assets/2a7ed2e9-5bd1-43ae-bac2-9cdcf663561d)
![image](https://github.com/user-attachments/assets/d2ec8772-bd3c-42c2-be71-11ea0b60701b)
![Uploading Screenshot 2024-12-24 225716.png…]()
