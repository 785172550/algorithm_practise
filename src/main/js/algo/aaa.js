//画布大小
var width = 1200;
var height = 600;


const margin = {left: 150, right: 150, top: 50, bottom: 50};
const innerWidth = width - margin.left - margin.right;
const innerHeight = height - margin.top - margin.bottom;

//在 body 里添加一个 SVG 画布
var svg = d3.select("body")
  .append("svg")
  .attr("width", width)
  .attr("height", height);

//画布周边的空白
// var padding = {left: 30, right: 30, top: 20, bottom: 20};


//定义一个数组
var dataset = [10, 20, 30, 40, 33, 24, 12, 5];

//x轴的比例尺
var xScale = d3.scaleBand()
  .domain(d3.range(dataset.length))
  .padding(0.2)
  .range([0, innerWidth]);

//y轴的比例尺
var yScale = d3.scaleLinear()
  .domain([0, d3.max(dataset)])
  .range([innerHeight, 0]);


//定义x轴
var xAxis = d3.axisBottom(xScale);

//定义y轴
var yAxis = d3.axisLeft(yScale);

const g = svg.append('g').attr('transform', `translate(${margin.left},${margin.top})`);

//添加x轴
g.append("g").attr("class", "axis").attr("transform", `translate(0,${innerHeight})`).call(xAxis);

g.append("g").call(yAxis);


//添加矩形元素
var rects = g.selectAll("rect")
  .data(dataset)
  .enter()
  .append("rect")
  .attr("x", function (d, i) {
    return xScale(i);
  })
  .attr("y", function (d) {
    return yScale(d);
  })
  .attr("width", xScale.bandwidth())
  .attr("height", function (d) {
    return innerHeight - yScale(d);
  });

// //添加文字元素
var texts = g.selectAll(".MyText")
  .data(dataset)
  .enter()
  .append("text")
  .attr("class", "MyText")
  .attr("x", (d, i) => xScale(i) + xScale.bandwidth() / 2)
  .attr("y", d => yScale(d) - 10)
  // .attr("dx", function (d) {
  //   return xScale.bandwidth() - xScale.bandwidth() / 2;
  // })
  // .attr("dy", function (d) {
  //   return 20;
  // })
  .text(function (d) {
    return d;
  });
