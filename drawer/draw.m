
function draw(static_file, dynamic_file, output_file, m, particle)
  fid = fopen(static_file,'r');
  N = str2num(fgetl(fid));
  L = str2num(fgetl(fid));
  fclose(fid);
  [radius colour] = textread(static_file,"%f %f", 'headerLines', 2);
  [x y] = textread(dynamic_file,"%f %f", 'headerLines', 1);
  fid = fopen(output_file,'r');
  neighbour={};
  for i = 1:N
    line = fgetl(fid);
    neighbour{end+1} = textscan(line,'%d');
  end
  
  
  plot = scatter(x,y, 'b','filled');
  title('Index Cell Method');
  xlabel('X coordinate');
  ylabel('Y coordinate');
  hold on;
  queryX = x(particle);
  queryY = y(particle);
  plot = scatter(queryX,queryY,'g', 'filled');
  highlighted = neighbour{particle}{1}(2:end);
  queryX1 = x(highlighted);
  queryY1 = y(highlighted);
  queryR = radius(highlighted);
  plot = scatter(queryX1,queryY1,'r', 'filled');
  set(gca,'xtick',[0:(L/m):L]);
  set(gca,'ytick',[0:(L/m):L]);
  grid on;
  axis('square');
  
endfunction